package com.example.asm.Service;

import com.example.asm.Model.DongSP;

import java.util.List;
import java.util.UUID;

public interface DongSPService {
    List<DongSP> getAll();

    DongSP getOne(UUID id);

    String add(DongSP dongSP);

    String update(DongSP dongSP);

    String delete(DongSP dongSP);
}
