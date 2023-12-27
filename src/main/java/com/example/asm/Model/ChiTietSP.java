package com.example.asm.Model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ChiTietSP")
public class ChiTietSP implements Serializable {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "IdSP",referencedColumnName = "Id")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "IdNsx",referencedColumnName = "Id")
    private NSX nsx;

    @ManyToOne
    @JoinColumn(name = "IdMauSac",referencedColumnName = "Id")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IdDongSP",referencedColumnName = "Id")
    private DongSP dongSP;

    @Column(name = "NamBH")
    private int namBH;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "SoLuongTon")
    private int soLuongTon;

    @Column(name = "GiaNhap")
    private Float giaNhap;

    @Column(name = "GiaBan")
    private Float giaBan;

}
