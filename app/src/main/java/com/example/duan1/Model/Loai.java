package com.example.duan1.Model;

public class Loai {


    private Integer maLoai;
    private String tenLoai;
    private String trangThai;
    private Integer soTK;

    public Loai() {
    }

    public Loai(Integer maLoai, String tenLoai, String trangThai, Integer soTK) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;
        this.soTK = soTK;

    }

    public Loai(String tenLoai, String trangThai) {
        this.tenLoai = tenLoai;
        this.trangThai = trangThai;

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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoTK() {
        return soTK;
    }

    public void setSoTK(Integer soTK) {
        this.soTK = soTK;
    }

}
