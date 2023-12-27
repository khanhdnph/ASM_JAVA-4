package com.example.asm.Service;

import com.example.asm.Model.SanPham;

import java.util.List;
import java.util.UUID;

public interface SanPhamService {
    List<SanPham> getAll();

    SanPham getOne(UUID id);

    String add(SanPham sanPham);

    String update(SanPham sanPham);

    String delete(SanPham sanPham);
}
