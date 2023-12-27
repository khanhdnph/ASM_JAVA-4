package com.example.asm.Service;

import com.example.asm.Model.CuaHang;

import java.util.List;
import java.util.UUID;

public interface CuaHangService {
    List<CuaHang> getAll();

    CuaHang getOne(UUID id);

    String add(CuaHang cuaHang);

    String update(CuaHang cuaHang);

    String delete(CuaHang cuaHang);
}
