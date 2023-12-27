package com.example.asm.Servlet;

import com.example.asm.Model.SanPham;
import com.example.asm.Service.SanPhamService;
import com.example.asm.Service.ipml.SanPhamServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "SanPhamServlet", value = {
        "/san-pham/hien-thi",
        "/san-pham/add",
        "/san-pham/update",
        "/san-pham/delete",
        "/san-pham/detail",
        "/san-pham/view-add",
        "/san-pham/view-update",
})
public class SanPhamServlet extends HttpServlet {
    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private List<SanPham> listSanPham = new ArrayList<>();
    private String id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
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

        SanPham sanPham =sanPhamService.getOne(UUID.fromString(id));
        sanPham.setMa( request.getParameter("ma"));
        sanPham.setTen( request.getParameter("ten"));
        request.setAttribute("mess", sanPhamService.update(sanPham));
        listSanPham = sanPhamService.getAll();
        request.setAttribute("listSP", listSanPham);
        request.getRequestDispatcher("/sanpham/trang-chu.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SanPham sanPham = SanPham.builder()
                .ma(request.getParameter("ma"))
                .ten(request.getParameter("ten"))
                .build();
        request.setAttribute("mess", sanPhamService.add(sanPham));
        listSanPham = sanPhamService.getAll();
        request.setAttribute("listSP", listSanPham);
        request.getRequestDispatcher("/sanpham/trang-chu.jsp").forward(request, response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        SanPham sp = sanPhamService.getOne(UUID.fromString(id));
        request.setAttribute("sp", sp);
        listSanPham = sanPhamService.getAll();
        request.setAttribute("listSP", listSanPham);
        request.getRequestDispatcher("/sanpham/update.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/sanpham/add.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        SanPham sp = sanPhamService.getOne(UUID.fromString(id));
        request.setAttribute("mess", sanPhamService.delete(sp));
        listSanPham = sanPhamService.getAll();
        request.setAttribute("listSP", listSanPham);
        request.getRequestDispatcher("/sanpham/trang-chu.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        SanPham sp = sanPhamService.getOne(UUID.fromString(id));
        request.setAttribute("sp", sp);
        listSanPham = sanPhamService.getAll();
        request.setAttribute("listSP", listSanPham);
        request.getRequestDispatcher("/sanpham/detail.jsp").forward(request, response);
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listSanPham = sanPhamService.getAll();
        request.setAttribute("listSP", listSanPham);
        request.getRequestDispatcher("/sanpham/trang-chu.jsp").forward(request, response);
    }
}
