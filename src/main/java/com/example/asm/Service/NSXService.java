package com.example.asm.Service;

import com.example.asm.Model.NSX;

import java.util.List;
import java.util.UUID;

public interface NSXService {
    List<NSX> getAll();

    NSX getOne(UUID id);

    String add(NSX nsx);

    String update(NSX nsx);

    String delete(NSX nsx);
}
