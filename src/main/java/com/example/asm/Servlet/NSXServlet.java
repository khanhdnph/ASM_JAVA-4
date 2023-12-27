package com.example.asm.Servlet;

import com.example.asm.Model.NSX;
import com.example.asm.Service.NSXService;
import com.example.asm.Service.ipml.NSXServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "NSXServlet", value =  {
        "/nsx/hien-thi",
        "/nsx/add",
        "/nsx/update",
        "/nsx/delete",
        "/nsx/detail",
        "/nsx/view-add",
        "/nsx/view-update",
})
public class NSXServlet extends HttpServlet {
    private NSXService nsxService = new NSXServiceImpl();
    private List<NSX> lisNSX = new ArrayList<>();
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
        NSX nsx = nsxService.getOne(UUID.fromString(id));
        nsx.setMa(request.getParameter("ma"));
        nsx.setTen(request.getParameter("ten"));
        request.setAttribute("mess",nsxService.update(nsx));
        lisNSX = nsxService.getAll();
        request.setAttribute("listNSX",lisNSX);
        request.getRequestDispatcher("/nsx/trang-chu.jsp").forward(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        NSX nsx = NSX.builder()
                .ma(request.getParameter("ma"))
                .ten(request.getParameter("ten"))
                .build();
        request.setAttribute("mess",nsxService.add(nsx));
        lisNSX = nsxService.getAll();
        request.setAttribute("listNSX",lisNSX);
        request.getRequestDispatcher("/nsx/trang-chu.jsp").forward(request,response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        NSX nsx = nsxService.getOne(UUID.fromString(id));
        request.setAttribute("nsx",nsx);
        lisNSX = nsxService.getAll();
        request.setAttribute("listNSX",lisNSX);
        request.getRequestDispatcher("/nsx/update.jsp").forward(request,response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/nsx/add.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        NSX nsx = nsxService.getOne(UUID.fromString(id));
        request.setAttribute("mess",nsxService.delete(nsx));
        lisNSX = nsxService.getAll();
        request.setAttribute("listNSX",lisNSX);
        request.getRequestDispatcher("/nsx/trang-chu.jsp").forward(request,response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        NSX nsx = nsxService.getOne(UUID.fromString(id));
        request.setAttribute("nsx",nsx);
        lisNSX = nsxService.getAll();
        request.setAttribute("listNSX",lisNSX);
        request.getRequestDispatcher("/nsx/detail.jsp").forward(request,response);
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        lisNSX = nsxService.getAll();
        request.setAttribute("listNSX",lisNSX);
        request.getRequestDispatcher("/nsx/trang-chu.jsp").forward(request,response);
    }
}
