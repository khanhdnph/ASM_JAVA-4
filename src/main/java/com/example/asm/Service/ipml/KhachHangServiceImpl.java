package com.example.asm.Service.ipml;

import com.example.asm.Model.KhachHang;
import com.example.asm.Repository.KhachHangRepository;
import com.example.asm.Service.KhachHangService;

import java.util.List;
import java.util.UUID;

public class KhachHangServiceImpl implements KhachHangService {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.getAll();
    }

    @Override
    public KhachHang getOne(UUID id) {
        return khachHangRepository.getOne(id);
    }

    @Override
    public String add(KhachHang khachHang) {
        if(khachHang.getMa().isEmpty() || khachHang.getTen().isEmpty()
                ||khachHang.getTenDem().isEmpty() || khachHang.getHo().isEmpty()
                || khachHang.getNgaySinh().isEmpty() || khachHang.getSdt().isEmpty()
                ||khachHang.getDiaChi().isEmpty() || khachHang.getThanhPho().isEmpty()
                ||khachHang.getQuocGia().isEmpty() || khachHang.getMatKhau().isEmpty()){
            return "Nhập thông tin đầy đủ";
        }else{
            boolean add = khachHangRepository.add(khachHang);
            if (add) {
                return "add thanh cong";
            } else {
                return "add that bai";
            }
        }

    }

    @Override
    public String update(KhachHang khachHang) {
        boolean update = khachHangRepository.update(khachHang);
        if (update) {
            return "update thanh cong";
        } else {
            return "update that bai";
        }
    }

    @Override
    public String delete(KhachHang khachHang) {
        boolean delete = khachHangRepository.delete(khachHang);
        if (delete) {
            return "delete thanh cong";
        } else {
            return "delete that bai";
        }
    }
}
