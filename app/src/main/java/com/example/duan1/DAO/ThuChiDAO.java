package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.Model.KhoanThuChi;
import com.example.duan1.Model.LichSu;
import com.example.duan1.Model.Loai;
import com.example.duan1.Model.ThongKe;
import com.example.duan1.database.DataHelper;

import java.util.ArrayList;

public class ThuChiDAO {
    DataHelper dataHelper;

    public ThuChiDAO(Context context) {
        dataHelper = new DataHelper(context);
    }


    // get danh sách loại thu/chi

    public ArrayList<Loai> getDsLoaiThuChi(String loai) {
        ArrayList<Loai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAI", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String trangthai = cursor.getString(2);
                if (trangthai.equals(loai)) {
                    list.add(new Loai(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
    // them loai thu/chi

    public boolean addLoaiThuChi(Loai loai) {
        SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloai", loai.getTenLoai());
        contentValues.put("trangthai", loai.getTrangThai());
        contentValues.put("soTK", loai.getSoTK());
        long check = sqLiteDatabase.insert("LOAI",  null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    //cap nhat loai thu chi
    public boolean updateLoaiThuChi(Loai loai) {
        SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maloai", loai.getMaLoai());
        contentValues.put("tenLoai", loai.getTenLoai());
        contentValues.put("trangthai", loai.getTrangThai());
        long check = sqLiteDatabase.update("LOAI", contentValues, "maloai = ?", new String[]{String.valueOf(loai.getMaLoai())});
        if (check == -1)
            return false;
        return true;
    }

    // xoa loai thu chi
    public boolean deleteLoaiThuChi(int maLoai) {
        SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("LOAI", "maLoai = ?", new String[]{String.valueOf(maLoai)});
        if (check == -1)
            return false;
        return true;
    }

    //lay danh sach khoan thu chi
    public ArrayList<KhoanThuChi> getDSKhoanThuChi(String loai) {
        ArrayList<KhoanThuChi> list = new ArrayList<>();
        SQLiteDatabase database = dataHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT k.maKhoan, k.tien, k.tiendaco, l.tenLoai, k.ngay, k.maLoai FROM LOAI l, KHOANTHUCHI k WHERE l.maLoai = k.maLoai AND l.trangthai = ? ", new String[]{loai});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new KhoanThuChi(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //lay danh sach 1 khoan thu chi
    public ArrayList<KhoanThuChi> getDSchitiet(String loai, Integer makhoan){
        ArrayList<KhoanThuChi> list = new ArrayList<>();
        KhoanThuChi khoanThuChi = new KhoanThuChi();
        SQLiteDatabase database = dataHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT k.maKhoan, k.tien, k.tiendaco, l.tenLoai, k.ngay, k.maLoai FROM LOAI l, KHOANTHUCHI k WHERE l.maLoai = k.maLoai AND l.trangthai = ? and k.makhoan =?", new String[]{loai, String.valueOf(makhoan)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
//                khoanThuChi.setMaKhoan(cursor.getInt(0));
//                khoanThuChi.getTien(cursor.getInt(1));
//                khoanThuChi.getTienDaCo(cursor.getInt(2));
                list.add(new KhoanThuChi(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4),cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        return list;
    }

    // them khoan thu chi
    public boolean addKhoanThuChi(KhoanThuChi khoanThuChi) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tien", khoanThuChi.getTien());
        contentValues.put("tiendaco",0);
        contentValues.put("ngay", khoanThuChi.getNgay());
        contentValues.put("maLoai", khoanThuChi.getMaLoai());
        long check = database.insert("KHOANTHUCHI", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    //cap nhat khoan thu chi
    public boolean updateKhoanThuChi(KhoanThuChi khoanThuChi) {
        SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maKhoan", khoanThuChi.getMaKhoan());
        contentValues.put("tien", khoanThuChi.getTien());
        contentValues.put("tiendaco",khoanThuChi.getTienDaCo());
        contentValues.put("ngay", khoanThuChi.getNgay());
        contentValues.put("maLoai", khoanThuChi.getMaLoai());
        long check = sqLiteDatabase.update("KHOANTHUCHI", contentValues, "maKhoan = ?", new String[]{String.valueOf(khoanThuChi.getMaKhoan())});
        if (check == -1)
            return false;
        return true;
    }

    //xoa khoan thu chi
    public boolean deleteKhoanThuChi(int maKhoan) {
        SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("KHOANTHUCHI", "maKhoan = ?", new String[]{String.valueOf(maKhoan)});
        if (check == -1)
            return false;
        return true;
    }

    public float[] getThongTinThuChi(int id) {
        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        int thu = 0, chi = 0;
        //select sum(tien)
        //from giaodich
        //where maloai in (select maloai from phanloai where thangthai = 'thu')
        Cursor cursorThu = sqLiteDatabase.rawQuery("SELECT sum(tien) from KHOANTHUCHI where maloai in (select maloai FROM LOAI where trangthai = 'thu'and soTK = ?) ", new String[]{String.valueOf(id)});
        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            thu = cursorThu.getInt(0);
        }
        //select sum(tien)
        //from giaodich
        //where maloai in (select maloai from phanloai where thangthai = 'chi')
        Cursor cursorChi = sqLiteDatabase.rawQuery("select sum(tien) from KHOANTHUCHI where maloai in (select maloai from LOAI where trangthai = 'chi'and soTK = ?) ", new String[]{String.valueOf(id)});
        if (cursorChi.getCount() != 0) {
            cursorChi.moveToFirst();
            chi = cursorChi.getInt(0);
        }
        float[] ketQua = new float[]{thu, chi};
        return ketQua;
    }

    public Integer[] getDoanhThuTheoThang( String ngaybatdau, String ngayketthuc) { //2022/09/30
        ngaybatdau = ngaybatdau.replace("/", "");
        ngayketthuc = ngayketthuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        int thu = 0, chi = 0;
        //                                                 SELECT SUM(tien) FROM KHOANTHUCHI WHERE maLoai in (select maLoai FROM LOAI where trangthai = 'chi' and soTK = 2) and substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between '20220101' and '20221119'
        Cursor cursorThu = sqLiteDatabase.rawQuery("SELECT SUM(tien) FROM KHOANTHUCHI WHERE maloai in (select maloai FROM LOAI where trangthai = 'thu') and substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?", new String[]{ ngaybatdau, ngayketthuc});
        if (cursorThu.getCount() != 0) {
            cursorThu.moveToFirst();
            thu = cursorThu.getInt(0);
        }
        Cursor cursorChi = sqLiteDatabase.rawQuery("SELECT SUM(tien) FROM KHOANTHUCHI WHERE maLoai in (select maLoai FROM LOAI where trangthai = 'chi') and substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?", new String[]{ ngaybatdau, ngayketthuc});
        if (cursorChi.getCount() != 0) {
            cursorChi.moveToFirst();
            chi = cursorChi.getInt(0);
        }
        Integer[] ketQua = new Integer[]{thu, chi};
        return ketQua;
    }

    public Integer getDoanhThuNam(String ngaybatdau, String ngayketthuc, String loai) { //2022/09/30
        ngaybatdau = ngaybatdau.replace("/", "");
        ngayketthuc = ngayketthuc.replace("/", "");
        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        Integer thu1 = 0, chi1 = 0;
            Cursor cursorThu = sqLiteDatabase.rawQuery("SELECT SUM(tien) FROM KHOANTHUCHI WHERE maLoai in (select maLoai FROM LOAI where trangthai = 'thu' ) and substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?", new String[]{ ngaybatdau, ngayketthuc});
            if (cursorThu.getCount() != 0) {
                cursorThu.moveToFirst();
                thu1 = cursorThu.getInt(0);
            }
            Cursor cursorChi = sqLiteDatabase.rawQuery("SELECT SUM(tien) FROM KHOANTHUCHI WHERE maLoai in (select maLoai FROM LOAI where trangthai = 'chi' ) and substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?", new String[]{ ngaybatdau, ngayketthuc});
            if (cursorChi.getCount() != 0) {
                cursorChi.moveToFirst();
                chi1 = cursorChi.getInt(0);
            }
            if (loai == "thu"){
                return thu1;
            }else {
                return chi1;
            }
    }

    //them lich su
    public boolean addLichSu(LichSu lichSu) {
        SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", lichSu.getTen());
        contentValues.put("tien", lichSu.getTien());
        contentValues.put("ngay", lichSu.getNgay());
        long check = sqLiteDatabase.insert("LICHSU",  null, contentValues);
        if (check == -1)
            return false;
        return true;
    }
    //hiện thị lịch sử
    public ArrayList<LichSu> getDsLichSu() {
        ArrayList<LichSu> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LICHSU", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                    list.add(new LichSu(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3)));

            } while (cursor.moveToNext());
        }
        return list;
    }

}
