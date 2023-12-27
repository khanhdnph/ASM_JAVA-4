package com.example.asm.Service;

import com.example.asm.Model.NhanVien;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {
    List<NhanVien> getAll();

    NhanVien getOne(UUID id);

    String add(NhanVien nhanVien);

    String update(NhanVien nhanVien);

    String delete(NhanVien nhanVien);
}
