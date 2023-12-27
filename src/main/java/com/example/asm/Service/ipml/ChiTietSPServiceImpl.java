package com.example.asm.Service.ipml;

import com.example.asm.Model.ChiTietSP;
import com.example.asm.Repository.ChiTietSPRepository;
import com.example.asm.Service.ChiTietSPService;

import java.util.List;
import java.util.UUID;

public class ChiTietSPServiceImpl implements ChiTietSPService {
    private ChiTietSPRepository chiTietSPRepository = new ChiTietSPRepository();

    @Override
    public List<ChiTietSP> getAll() {
        return chiTietSPRepository.getAll();
    }

    @Override
    public ChiTietSP getOne(UUID id) {
        return chiTietSPRepository.getOne(id);
    }

    @Override
    public String add(ChiTietSP chiTietSP) {
        String namBH = String.valueOf(chiTietSP.getNamBH());
        String soLuongTon = String.valueOf(chiTietSP.getSoLuongTon());
        String giaNhap = String.valueOf(chiTietSP.getGiaNhap());
        String giaBan = String.valueOf(chiTietSP.getGiaBan());
        if (namBH.isEmpty() || giaBan.isEmpty() || giaNhap.isEmpty() || soLuongTon.isEmpty() || chiTietSP.getMoTa().isEmpty()) {
            return "Nhập đầy đủ thông tin";
        } else {
            boolean add = chiTietSPRepository.add(chiTietSP);
            if (add) {
                return "Thêm sản phẩm thành công";
            } else {
                return "Thêm sản phẩm thất  bại";
            }
        }
    }

    @Override
    public String update(ChiTietSP chiTietSP) {
        boolean update = chiTietSPRepository.update(chiTietSP);
        if (update) {
            return "Sửa sản phẩm thành công";
        } else {
            return "Sửa  sản phẩm thất bại";
        }

    }

    @Override
    public String delete(ChiTietSP chiTietSP) {
        boolean delete = chiTietSPRepository.delete(chiTietSP);
        if (delete) {
            return "Xóa sản phẩm thành công";
        } else {
            return "Xóa sản phẩm thất bại";
        }

    }
}
