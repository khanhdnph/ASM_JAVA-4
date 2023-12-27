package com.example.asm.Service.ipml;

import com.example.asm.Model.NSX;
import com.example.asm.Repository.NSXRepository;
import com.example.asm.Service.NSXService;

import java.util.List;
import java.util.UUID;

public class NSXServiceImpl implements NSXService {
    private NSXRepository nsxRepository = new NSXRepository();

    @Override
    public List<NSX> getAll() {
        return nsxRepository.getAll();
    }

    @Override
    public NSX getOne(UUID id) {
        return nsxRepository.getOne(id);
    }

    @Override
    public String add(NSX nsx) {
        if(nsx.getMa().isEmpty() || nsx.getTen().isEmpty()){
            return "Nhập đầy đủ thông tin";
        }else{
            boolean add = nsxRepository.add(nsx);
            if (add) {
                return "add thanh cong";
            } else {
                return "add that bai";
            }
        }

    }

    @Override
    public String update(NSX nsx) {

        boolean update = nsxRepository.update(nsx);
        if (update) {
            return "update thanh cong";
        } else {
            return "update that bai";
        }

    }

    @Override
    public String delete(NSX nsx) {
        boolean delete = nsxRepository.delete(nsx);
        if (delete) {
            return "delete thanh cong";
        } else {
            return "delete that bai";
        }
    }
}
