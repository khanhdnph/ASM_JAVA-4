package com.example.asm.Service.ipml;

import com.example.asm.Model.NhanVien;
import com.example.asm.Repository.NhanVienRepository;
import com.example.asm.Service.NhanVienService;

import java.util.List;
import java.util.UUID;

public class NhanVienServiceImpl implements NhanVienService {
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.getAll();
    }

    @Override
    public NhanVien getOne(UUID id) {
        return nhanVienRepository.getOne(id);
    }

    @Override
    public String add(NhanVien nhanVien) {
        String trangThai = String.valueOf(nhanVien.getTrangThai());
        if(nhanVien.getHo().isEmpty() || nhanVien.getMa().isEmpty()
                || nhanVien.getTen().isEmpty() || nhanVien.getTenDem().isEmpty() || trangThai.isEmpty()
                || nhanVien.getGioiTinh().isEmpty() || nhanVien.getNgaySinh().isEmpty()
                || nhanVien.getDiaChi().isEmpty() || nhanVien.getSdt().isEmpty()
                || nhanVien.getMatKhau().isEmpty()){
            return "Nhập thông tin đầy đủ";
        }else{
            boolean add = nhanVienRepository.add(nhanVien);
            if (add) {
                return "Thêm Thành Công";
            } else {
                return "Thêm Thất Bại";
            }
        }
    }

    @Override
    public String update(NhanVien nhanVien) {
        boolean update = nhanVienRepository.update(nhanVien);
        if (update) {
            return "update thanh cong";
        } else {
            return "update that bai";
        }
    }

    @Override
    public String delete(NhanVien nhanVien) {
        boolean delete = nhanVienRepository.delete(nhanVien);
        if (delete) {
            return "delete thanh cong";
        } else {
            return "delete that bai";
        }
    }
}
