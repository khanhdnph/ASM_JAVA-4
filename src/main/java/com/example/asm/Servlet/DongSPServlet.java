package com.example.asm.Servlet;

import com.example.asm.Model.DongSP;
import com.example.asm.Service.DongSPService;
import com.example.asm.Service.ipml.DongSPServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "DongSPServlet", value =  {
        "/dongsp/hien-thi",
        "/dongsp/add",
        "/dongsp/update",
        "/dongsp/detail",
        "/dongsp/delete",
        "/dongsp/view-add",
        "/dongsp/view-update",
})
public class DongSPServlet extends HttpServlet {
    private DongSPService dongSPService = new DongSPServiceImpl();
    private List<DongSP> listDongSP = new ArrayList<>();
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
        DongSP dsp = dongSPService.getOne(UUID.fromString(id));
        dsp.setMa(request.getParameter("ma"));
        dsp.setTen(request.getParameter("ten"));
        request.setAttribute("mess",dongSPService.update(dsp));
        listDongSP = dongSPService.getAll();
        request.setAttribute("listDSP",listDongSP);
        request.getRequestDispatcher("/dongsanpham/trang-chu.jsp").forward(request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DongSP dsp = DongSP.builder()
                .ma(request.getParameter("ma"))
                .ten(request.getParameter("ten"))
                .build();
        request.setAttribute("mess",dongSPService.add(dsp));
        listDongSP = dongSPService.getAll();
        request.setAttribute("listDSP",listDongSP);
        request.getRequestDispatcher("/dongsanpham/trang-chu.jsp").forward(request,response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        DongSP dsp = dongSPService.getOne(UUID.fromString(id));
        request.setAttribute("dsp",dsp);
        listDongSP = dongSPService.getAll();
        request.setAttribute("listDSP",listDongSP);
        request.getRequestDispatcher("/dongsanpham/update.jsp").forward(request,response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/dongsanpham/add.jsp").forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        DongSP dsp = dongSPService.getOne(UUID.fromString(id));
        request.setAttribute("mess",dongSPService.delete(dsp));
        listDongSP = dongSPService.getAll();
        request.setAttribute("listDSP",listDongSP);
        request.getRequestDispatcher("/dongsanpham/trang-chu.jsp").forward(request,response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        DongSP dsp = dongSPService.getOne(UUID.fromString(id));
        request.setAttribute("dsp",dsp);
        listDongSP = dongSPService.getAll();
        request.setAttribute("listDSP",listDongSP);
        request.getRequestDispatcher("/dongsanpham/detail.jsp").forward(request,response);
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listDongSP = dongSPService.getAll();
        request.setAttribute("listDSP",listDongSP);
        request.getRequestDispatcher("/dongsanpham/trang-chu.jsp").forward(request,response);
    }
}
