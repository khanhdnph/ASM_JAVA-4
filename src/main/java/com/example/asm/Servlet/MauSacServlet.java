package com.example.asm.Servlet;

import com.example.asm.Model.MauSac;
import com.example.asm.Service.MauSacService;
import com.example.asm.Service.ipml.MauSacServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "MauSacServlet", value = {
        "/mau-sac/hien-thi",
        "/mau-sac/add",
        "/mau-sac/update",
        "/mau-sac/delete",
        "/mau-sac/detail",
        "/mau-sac/view-add",
        "/mau-sac/view-update",
})
public class MauSacServlet extends HttpServlet {

    private MauSacService mauSacService = new MauSacServiceImpl();
    private List<MauSac> listMauSac = new ArrayList<>();
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
        }else{
            this.hienThi(request,response);
        }
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MauSac ms = mauSacService.getOne(UUID.fromString(id));
        ms.setMa( request.getParameter("ma"));
        ms.setTen(request.getParameter("ten"));
        request.setAttribute("mess",mauSacService.update(ms));
        listMauSac = mauSacService.getAll();
        request.setAttribute("listMS",listMauSac);
        request.getRequestDispatcher("/mausac/trang-chu.jsp").forward(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MauSac ms = MauSac.builder()
                .ma(request.getParameter("ma"))
                .ten(request.getParameter("ten"))
                .build();
        request.setAttribute("mess",mauSacService.add(ms));
        listMauSac = mauSacService.getAll();
        request.setAttribute("listMS",listMauSac);
        request.getRequestDispatcher("/mausac/trang-chu.jsp").forward(request,response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        MauSac ms = mauSacService.getOne(UUID.fromString(id));
        request.setAttribute("ms",ms);
        listMauSac = mauSacService.getAll();
        request.setAttribute("listMS",listMauSac);
        request.getRequestDispatcher("/mausac/update.jsp").forward(request,response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/mausac/add.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        MauSac ms = mauSacService.getOne(UUID.fromString(id));
        request.setAttribute("mess",mauSacService.delete(ms));
        listMauSac = mauSacService.getAll();
        request.setAttribute("listMS",listMauSac);
        request.getRequestDispatcher("/mausac/trang-chu.jsp").forward(request,response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        MauSac ms = mauSacService.getOne(UUID.fromString(id));
        request.setAttribute("ms",ms);
        listMauSac = mauSacService.getAll();
        request.setAttribute("listMS",listMauSac);
        request.getRequestDispatcher("/mausac/detail.jsp").forward(request,response);
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listMauSac = mauSacService.getAll();
        request.setAttribute("listMS",listMauSac);
        request.getRequestDispatcher("/mausac/trang-chu.jsp").forward(request,response);
    }
}
