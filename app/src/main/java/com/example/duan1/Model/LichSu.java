package com.example.duan1.Model;

public class LichSu {
    private Integer mals;
    private String ten;
    private Integer tien;
    private String ngay;

    public LichSu() {
    }

    public LichSu(Integer mals, String ten, Integer tien, String ngay) {
        this.mals = mals;
        this.ten = ten;
        this.tien = tien;
        this.ngay = ngay;
    }

    public Integer getMals() {
        return mals;
    }

    public void setMals(Integer mals) {
        this.mals = mals;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getTien() {
        return tien;
    }

    public void setTien(Integer tien) {
        this.tien = tien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
