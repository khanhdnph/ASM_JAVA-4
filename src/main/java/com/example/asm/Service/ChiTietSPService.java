package com.example.asm.Service;

import com.example.asm.Model.ChiTietSP;

import java.util.List;
import java.util.UUID;

public interface ChiTietSPService {
    List<ChiTietSP> getAll();

    ChiTietSP getOne(UUID id);

    String add(ChiTietSP chiTietSP);

    String update(ChiTietSP chiTietSP);

    String delete(ChiTietSP chiTietSP);
}
