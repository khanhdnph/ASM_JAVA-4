package com.example.asm.Servlet;

import com.example.asm.Model.ChiTietSP;
import com.example.asm.Service.*;
import com.example.asm.Service.ipml.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "ChiTietSPServlet", value ={
        "/chi-tiet-sp/add",
        "/chi-tiet-sp/update",
        "/chi-tiet-sp/detail",
        "/chi-tiet-sp/delete",
        "/chi-tiet-sp/view-add",
        "/chi-tiet-sp/view-update",
})
public class ChiTietSPServlet extends HttpServlet {
    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private List<ChiTietSP> listChiTietSP = new ArrayList<>();
    private String id;

    private SanPhamService sanPhamService = new SanPhamServiceImpl();
    private NSXService nsxService = new NSXServiceImpl();
    private MauSacService mauSacService = new MauSacServiceImpl();
    private DongSPService dongSPService = new DongSPServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiTatCa(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else if (uri.contains("view-add")) {
            this.viewAdd(request, response);
        } else if (uri.contains("view-update")) {
            this.viewUpdate(request, response);
        } else {
            this.hienThiTatCa(request, response);
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
            this.hienThiTatCa(request, response);

        }

    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChiTietSP ctsp = chiTietSPService.getOne(UUID.fromString(id));
        ctsp.setSanPham(sanPhamService.getOne(UUID.fromString(request.getParameter("sanPham"))));
        ctsp.setNsx(nsxService.getOne(UUID.fromString(request.getParameter("nsx"))));
        ctsp.setMauSac(mauSacService.getOne(UUID.fromString(request.getParameter("mauSac"))));
        ctsp.setDongSP(dongSPService.getOne(UUID.fromString(request.getParameter("dongSP"))));
        ctsp.setNamBH(Integer.valueOf(request.getParameter("namBH")));
        ctsp.setMoTa(request.getParameter("moTa"));
        ctsp.setSoLuongTon(Integer.valueOf(request.getParameter("soLuongTon")));
        ctsp.setGiaNhap(Float.valueOf(request.getParameter("giaNhap")));
        ctsp.setGiaBan(Float.valueOf(request.getParameter("giaBan")));

        request.setAttribute("mess",chiTietSPService.update(ctsp));
        listChiTietSP = chiTietSPService.getAll();
        request.setAttribute("listCTSP", listChiTietSP);
        request.getRequestDispatcher("/chitietsp/trang-chu.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChiTietSP chiTietSP = ChiTietSP.builder()
                .sanPham(sanPhamService.getOne(UUID.fromString(request.getParameter("sanPham"))))
                .nsx(nsxService.getOne(UUID.fromString(request.getParameter("nhaSanXuat"))))
                .mauSac(mauSacService.getOne(UUID.fromString(request.getParameter("mauSac"))))
                .dongSP(dongSPService.getOne(UUID.fromString(request.getParameter("dongSP"))))
                .namBH(parseInteger(request.getParameter("namBH")))
                .moTa(request.getParameter("moTa"))
                .soLuongTon(parseInteger(request.getParameter("soLuongTon")))
                .giaNhap(parseFloat(request.getParameter("giaNhap")))
                .giaBan(parseFloat(request.getParameter("giaBan")))
                .build();
        request.setAttribute("mess",chiTietSPService.add(chiTietSP));
        listChiTietSP = chiTietSPService.getAll();
        request.setAttribute("listCTSP", listChiTietSP);
        request.getRequestDispatcher("/chitietsp/trang-chu.jsp").forward(request, response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        id = request.getParameter("id");
        ChiTietSP ctsp =  chiTietSPService.getOne(UUID.fromString(id));
        request.setAttribute("ctsp",ctsp);
        listChiTietSP = chiTietSPService.getAll();
        request.setAttribute("listCTSP", listChiTietSP);
        request.setAttribute("listSP", sanPhamService.getAll());
        request.setAttribute("listNSX", nsxService.getAll());
        request.setAttribute("listMS", mauSacService.getAll());
        request.setAttribute("listDSP", dongSPService.getAll());
        request.getRequestDispatcher("/chitietsp/update.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listSP", sanPhamService.getAll());
        request.setAttribute("listNSX", nsxService.getAll());
        request.setAttribute("listMS", mauSacService.getAll());
        request.setAttribute("listDSP", dongSPService.getAll());
        request.getRequestDispatcher("/chitietsp/add.jsp").forward(request, response);

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        ChiTietSP ctsp =  chiTietSPService.getOne(UUID.fromString(id));
        request.setAttribute("mess",chiTietSPService.delete(ctsp));
        listChiTietSP = chiTietSPService.getAll();
        request.setAttribute("listCTSP", listChiTietSP);
        request.getRequestDispatcher("/chitietsp/trang-chu.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        ChiTietSP ctsp =  chiTietSPService.getOne(UUID.fromString(id));
        request.setAttribute("ctsp",ctsp);
        listChiTietSP = chiTietSPService.getAll();
        request.getRequestDispatcher("/chitietsp/detail.jsp").forward(request, response);

    }

    private void hienThiTatCa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listChiTietSP = chiTietSPService.getAll();
        request.setAttribute("listCTSP", listChiTietSP);
        request.setAttribute("listSP", sanPhamService.getAll());
        request.setAttribute("listNSX", nsxService.getAll());
        request.setAttribute("listMS", mauSacService.getAll());
        request.setAttribute("listDSP", dongSPService.getAll());
        request.getRequestDispatcher("/chitietsp/trang-chu.jsp").forward(request, response);
    }


    private int parseInteger(String number){
        if(number!=null && number.length()>0){
            try {
                return Integer.parseInt(number);
            }catch (Exception e){
                return -1;
            }
        }
        else return 0;
    }

    private float parseFloat(String number){
        if(number!=null && number.length()>0){
            try {
                return Float.parseFloat(number);
            }catch (Exception e){
                return -1;
            }
        }
        else return 0;
    }
}
