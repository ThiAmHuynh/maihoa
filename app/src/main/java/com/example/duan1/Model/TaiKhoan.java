package com.example.duan1.Model;

public class TaiKhoan {
    private Integer soTK;
    private String taiKhoan;
    private String matKhau;
    private String soDT;

    public TaiKhoan() {

    }

    public TaiKhoan(String taiKhoan, String matKhau, String soDT) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.soDT = soDT;
    }

    public TaiKhoan(Integer soTK, String taiKhoan, String matKhau, String soDT) {
        this.soTK = soTK;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.soDT = soDT;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public Integer getSoTK() {
        return soTK;
    }

    public void setSoTK(Integer soTK) {
        this.soTK = soTK;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
