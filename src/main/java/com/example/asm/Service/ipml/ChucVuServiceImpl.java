package com.example.asm.Service.ipml;

import com.example.asm.Model.ChucVu;
import com.example.asm.Repository.ChucVuRepository;
import com.example.asm.Service.ChucVuService;

import java.util.List;
import java.util.UUID;

public class ChucVuServiceImpl implements ChucVuService {
    private ChucVuRepository chucVuRepository = new ChucVuRepository();

    @Override
    public List<ChucVu> getAll() {
        return chucVuRepository.getAll();
    }

    @Override
    public ChucVu getOne(UUID id) {
        return chucVuRepository.getOne(id);
    }

    @Override
    public String add(ChucVu chucVu) {
        if (chucVu.getMa().isEmpty() || chucVu.getTen().isEmpty()) {
            return "Nhập đầy đủ thông tin";
        } else {
            boolean add = chucVuRepository.add(chucVu);
            if (add) {
                return "add thanh cong";
            } else {
                return "add that bai";
            }
        }

    }

    @Override
    public String update(ChucVu chucVu) {
        boolean update = chucVuRepository.update(chucVu);
        if (update) {
            return "update thanh cong";
        } else {
            return "update that bai";
        }
    }

    @Override
    public String delete(ChucVu chucVu) {
        boolean delete = chucVuRepository.delete(chucVu);
        if (delete) {
            return "delete thanh cong";
        } else {
            return "delete that bai";
        }
    }
}
