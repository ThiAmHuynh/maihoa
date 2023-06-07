package com.example.duan1.Model;

public class KhoanThuChi {
    private Integer maKhoan;
    private Integer tien;
    private String tenKhoan;
    private String ngay;
    private Integer maLoai;
    private String tenLoai;
    private Integer tienDaCo;

    public KhoanThuChi(Integer maKhoan, Integer tien, String tenKhoan, String ngay, Integer maLoai, String tenLoai) {
        this.maKhoan = maKhoan;
        this.tien = tien;
        this.tenKhoan = tenKhoan;
        this.ngay = ngay;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public KhoanThuChi() {
    }

    public KhoanThuChi(Integer maKhoan, Integer tien,Integer tienDaCo, String tenKhoan, String ngay, Integer maLoai) {
        this.maKhoan = maKhoan;
        this.tien = tien;
        this.tienDaCo = tienDaCo;
        this.tenKhoan = tenKhoan;
        this.ngay = ngay;
        this.maLoai = maLoai;
    }

    public KhoanThuChi(Integer tien, Integer maLoai,String ngay) {
        this.tien = tien;
        this.maLoai = maLoai;
        this.ngay =ngay;
    }

    public KhoanThuChi(Integer tien, String ngay, Integer maLoai, Integer tienDaCo) {
        this.tien = tien;
        this.ngay = ngay;
        this.maLoai = maLoai;
        this.tienDaCo = tienDaCo;
    }

    public KhoanThuChi(Integer maKhoan, Integer tienDaCo) {
        this.maKhoan = maKhoan;
        this.tienDaCo = tienDaCo;
    }

    public Integer getTienDaCo() {
        return tienDaCo;
    }

    public void setTienDaCo(Integer tienDaCo) {
        this.tienDaCo = tienDaCo;
    }

    public Integer getMaKhoan() {
        return maKhoan;
    }

    public void setMaKhoan(Integer maKhoan) {
        this.maKhoan = maKhoan;
    }

    public Integer getTien() {
        return tien;
    }

    public void setTien(Integer tien) {
        this.tien = tien;
    }

    public String getTenKhoan() {
        return tenKhoan;
    }

    public void setTenKhoan(String tenKhoan) {
        this.tenKhoan = tenKhoan;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public Integer getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(Integer maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
