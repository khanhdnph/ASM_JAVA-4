package com.example.asm.Service;

import com.example.asm.Model.MauSac;

import java.util.List;
import java.util.UUID;

public interface MauSacService {
    List<MauSac> getAll();

    MauSac getOne(UUID id);

    String add(MauSac mauSac);

    String update(MauSac mauSac);

    String delete(MauSac mauSac);
}
