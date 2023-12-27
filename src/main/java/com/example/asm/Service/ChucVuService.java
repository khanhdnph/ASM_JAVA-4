package com.example.asm.Service;

import com.example.asm.Model.ChucVu;

import java.util.List;
import java.util.UUID;

public interface ChucVuService {
    List<ChucVu> getAll();

    ChucVu getOne(UUID id);

    String add(ChucVu chucVu);

    String update(ChucVu chucVu);

    String delete(ChucVu chucVu);
}
