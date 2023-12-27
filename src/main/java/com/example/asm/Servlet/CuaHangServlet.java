package com.example.asm.Servlet;

import com.example.asm.Model.CuaHang;
import com.example.asm.Service.CuaHangService;
import com.example.asm.Service.ipml.CuaHangServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CuaHangServlet", value = {
        "/cua-hang/hien-thi",
        "/cua-hang/add",
        "/cua-hang/update",
        "/cua-hang/delete",
        "/cua-hang/detail",
        "/cua-hang/view-add",
        "/cua-hang/view-update",
})
public class CuaHangServlet extends HttpServlet {
    private CuaHangService cuaHangService = new CuaHangServiceImpl();
    private List<CuaHang> listCuaHang = new ArrayList<>();
    private String id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAdd(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
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
    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/cuahang/add.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        CuaHang ch = cuaHangService.getOne(UUID.fromString(id));
        request.setAttribute("mess", cuaHangService.delete(ch));
        listCuaHang = cuaHangService.getAll();
        request.setAttribute("listCH", listCuaHang);
        request.getRequestDispatcher("/cuahang/trang-chu.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        CuaHang ch = cuaHangService.getOne(UUID.fromString(id));
        request.setAttribute("ch", ch);
        request.getRequestDispatcher("/cuahang/detail.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuaHang ch = CuaHang.builder()
                .ma(request.getParameter("ma"))
                .ten(request.getParameter("ten"))
                .diaChi(request.getParameter("diaChi"))
                .thanhPho(request.getParameter("thanhPho"))
                .quocGia(request.getParameter("quocGia"))
                .build();
        request.setAttribute("mess", cuaHangService.add(ch));
        listCuaHang = cuaHangService.getAll();
        request.setAttribute("listCH", listCuaHang);
        request.getRequestDispatcher("/cuahang/trang-chu.jsp").forward(request, response);

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuaHang ch =cuaHangService.getOne(UUID.fromString(id));
        ch.setMa(request.getParameter("ma"));
        ch.setTen(request.getParameter("ten"));
        ch.setDiaChi(request.getParameter("diaChi"));
        ch.setThanhPho(request.getParameter("thanhPho"));
        ch.setQuocGia(request.getParameter("quocGia"));

        request.setAttribute("mess", cuaHangService.update(ch));
        listCuaHang = cuaHangService.getAll();
        request.setAttribute("listCH", listCuaHang);
        request.getRequestDispatcher("/cuahang/trang-chu.jsp").forward(request, response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        CuaHang ch = cuaHangService.getOne(UUID.fromString(id));
        request.setAttribute("ch", ch);
        request.getRequestDispatcher("/cuahang/update.jsp").forward(request, response);
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listCuaHang = cuaHangService.getAll();
        request.setAttribute("listCH", listCuaHang);
        request.getRequestDispatcher("/cuahang/trang-chu.jsp").forward(request, response);
    }
}
