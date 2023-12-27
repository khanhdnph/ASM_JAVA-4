package com.example.asm.Servlet;

import com.example.asm.Model.KhachHang;
import com.example.asm.Service.KhachHangService;
import com.example.asm.Service.ipml.KhachHangServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "KhachHangServlet", value = {
        "/khach-hang/hien-thi",
        "/khach-hang/add",
        "/khach-hang/update",
        "/khach-hang/delete",
        "/khach-hang/detail",
        "/khach-hang/view-add",
        "/khach-hang/view-update",
})
public class KhachHangServlet extends HttpServlet {
    private KhachHangService khachHangService = new KhachHangServiceImpl();
    private List<KhachHang> listKhachHang = new ArrayList<>();
    private String id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAdd(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(request, response);
        } else {
            this.hienThi(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add")) {
            this.add(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        } else {
            this.hienThi(request, response);
        }
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        KhachHang kh = khachHangService.getOne(UUID.fromString(id));
        kh.setMa( request.getParameter("ma"));
        kh.setTen(request.getParameter("ten"));
        kh.setTenDem(request.getParameter("tenDem"));
        kh.setHo( request.getParameter("ho"));
        kh.setNgaySinh(request.getParameter("ngaySinh"));
        kh.setSdt(request.getParameter("sdt"));
        kh.setDiaChi(request.getParameter("diaChi"));
        kh.setThanhPho(request.getParameter("thanhPho"));
        kh.setQuocGia(request.getParameter("quocGia"));
        kh.setMatKhau(request.getParameter("matKhau"));

        request.setAttribute("mess", khachHangService.update(kh));
        listKhachHang = khachHangService.getAll();
        request.setAttribute("listKH", listKhachHang);
        request.getRequestDispatcher("/khachhang/trang-chu.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        KhachHang kh = KhachHang.builder()
                .ma(request.getParameter("ma"))
                .ten(request.getParameter("ten"))
                .tenDem( request.getParameter("tenDem"))
                .ho(request.getParameter("ho"))
                .ngaySinh(request.getParameter("ngaySinh"))
                .sdt(request.getParameter("sdt"))
                .diaChi(request.getParameter("diaChi"))
                .thanhPho( request.getParameter("thanhPho"))
                .quocGia(request.getParameter("quocGia"))
                .matKhau(request.getParameter("matKhau"))
                .build();

        request.setAttribute("mess", khachHangService.add(kh));
        listKhachHang = khachHangService.getAll();
        request.setAttribute("listKH", listKhachHang);
        request.getRequestDispatcher("/khachhang/trang-chu.jsp").forward(request, response);

    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        KhachHang kh = khachHangService.getOne(UUID.fromString(id));
        request.setAttribute("kh", kh);
        listKhachHang = khachHangService.getAll();
        request.setAttribute("listKH", listKhachHang);
        request.getRequestDispatcher("/khachhang/update.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/khachhang/add.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        KhachHang kh = khachHangService.getOne(UUID.fromString(id));
        request.setAttribute("kh", kh);
        listKhachHang = khachHangService.getAll();
        request.setAttribute("listKH", listKhachHang);
        request.getRequestDispatcher("/khachhang/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        KhachHang khachHang = khachHangService.getOne(UUID.fromString(id));
        request.setAttribute("mess", khachHangService.delete(khachHang));
        listKhachHang = khachHangService.getAll();
        request.setAttribute("listKH", listKhachHang);
        request.getRequestDispatcher("/khachhang/trang-chu.jsp").forward(request, response);

    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listKhachHang = khachHangService.getAll();
        request.setAttribute("listKH", listKhachHang);
        request.getRequestDispatcher("/khachhang/trang-chu.jsp").forward(request, response);
    }
}
