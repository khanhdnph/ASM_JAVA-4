package com.example.asm.Servlet;

import com.example.asm.Model.ChucVu;
import com.example.asm.Model.CuaHang;
import com.example.asm.Model.NhanVien;
import com.example.asm.Service.ChucVuService;
import com.example.asm.Service.CuaHangService;
import com.example.asm.Service.NhanVienService;
import com.example.asm.Service.ipml.ChucVuServiceImpl;
import com.example.asm.Service.ipml.CuaHangServiceImpl;
import com.example.asm.Service.ipml.NhanVienServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "NhanVienServlet", value =  {
        "/nhan-vien/hien-thi",
        "/nhan-vien/add",
        "/nhan-vien/update",
        "/nhan-vien/delete",
        "/nhan-vien/detail",
        "/nhan-vien/view-add",
        "/nhan-vien/view-update",
})
public class NhanVienServlet extends HttpServlet {
    private NhanVienService nhanVienService = new NhanVienServiceImpl();
    private List<NhanVien> listNhanVien = new ArrayList<>();
    private ChucVuService chucVuService = new ChucVuServiceImpl();
    private List<ChucVu> listCV = new ArrayList<>();
    private CuaHangService cuaHangService = new CuaHangServiceImpl();
    private List<CuaHang> listCH = new ArrayList<>();
    private String id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiTatCa(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
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
        }
    }
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NhanVien nv = nhanVienService.getOne(UUID.fromString(id));
        nv.setMa(request.getParameter("ma"));
        nv.setTen(request.getParameter("ten"));
        nv.setTenDem(request.getParameter("tenDem"));
        nv.setHo(request.getParameter("ho"));
        nv.setGioiTinh(request.getParameter("gioiTinh"));
        nv.setNgaySinh(request.getParameter("ngaySinh"));
        nv.setDiaChi(request.getParameter("diaChi"));
        nv.setSdt(request.getParameter("sdt"));
        nv.setMatKhau(request.getParameter("matKhau"));
        nv.setCuaHang(cuaHangService.getOne(UUID.fromString(request.getParameter("cuaHang"))));
        nv.setChucVu(chucVuService.getOne(UUID.fromString(request.getParameter("chucVu"))));
        nv.setTrangThai(Integer.valueOf(request.getParameter("trangThai")));
        request.setAttribute("mess", nhanVienService.update(nv));
        listNhanVien = nhanVienService.getAll();
        request.setAttribute("listCV", chucVuService.getAll());
        request.setAttribute("listCH", cuaHangService.getAll());
        request.setAttribute("listNV", listNhanVien);
        request.getRequestDispatcher("/nhanvien/trang-chu.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NhanVien nv = NhanVien.builder()
                .ma(request.getParameter("ma"))
                .ten(request.getParameter("ten"))
                .tenDem(request.getParameter("tenDem"))
                .ho(request.getParameter("ho"))
                .gioiTinh(request.getParameter("gioiTinh"))
                .ngaySinh(request.getParameter("ngaySinh"))
                .diaChi(request.getParameter("diaChi"))
                .sdt(request.getParameter("sdt"))
                .matKhau(request.getParameter("matKhau"))
                .cuaHang(cuaHangService.getOne(UUID.fromString(request.getParameter("cuaHang"))))
                .chucVu(chucVuService.getOne(UUID.fromString(request.getParameter("chucVu"))))
                .trangThai(parseInteger(request.getParameter("trangThai")))
                .build();
        request.setAttribute("mess", nhanVienService.add(nv));
        listNhanVien = nhanVienService.getAll();
        request.setAttribute("listCV", chucVuService.getAll());
        request.setAttribute("listCH", cuaHangService.getAll());
        request.setAttribute("listNV", listNhanVien);
        request.getRequestDispatcher("/nhanvien/trang-chu.jsp").forward(request, response);
    }

    private void viewUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        NhanVien nv = nhanVienService.getOne(UUID.fromString(id));
        request.setAttribute("nv", nv);
        listNhanVien = nhanVienService.getAll();
        request.setAttribute("listCV", chucVuService.getAll());
        request.setAttribute("listCH", cuaHangService.getAll());
        request.setAttribute("listNV", listNhanVien);
        request.getRequestDispatcher("/nhanvien/update.jsp").forward(request, response);
    }

    private void viewAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listCV", chucVuService.getAll());
        request.setAttribute("listCH", cuaHangService.getAll());
        request.getRequestDispatcher("/nhanvien/add.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        NhanVien nv = nhanVienService.getOne(UUID.fromString(id));
        request.setAttribute("nv", nv);
        listNhanVien = nhanVienService.getAll();
        request.setAttribute("listCV", chucVuService.getAll());
        request.setAttribute("listCH", cuaHangService.getAll());
        request.setAttribute("listNV", listNhanVien);
        request.getRequestDispatcher("/nhanvien/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id = request.getParameter("id");
        NhanVien nv = nhanVienService.getOne(UUID.fromString(id));
        request.setAttribute("mess", nhanVienService.delete(nv));
        listNhanVien = nhanVienService.getAll();
        request.setAttribute("listNV", listNhanVien);
        request.getRequestDispatcher("/nhanvien/trang-chu.jsp").forward(request, response);
    }

    private void hienThiTatCa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listNhanVien = nhanVienService.getAll();
        request.setAttribute("listCV", chucVuService.getAll());
        request.setAttribute("listCH", cuaHangService.getAll());
        request.setAttribute("listNV", listNhanVien);
        request.getRequestDispatcher("/nhanvien/trang-chu.jsp").forward(request, response);
    }

    private int parseInteger(String number) {
        if (number != null && number.length() > 0) {
            try {
                return Integer.parseInt(number);
            } catch (Exception e) {
                return -1;
            }
        } else return 0;
    }
}
