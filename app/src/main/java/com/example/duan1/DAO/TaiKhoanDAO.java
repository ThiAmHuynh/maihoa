package com.example.duan1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duan1.Model.Loai;
import com.example.duan1.Model.TaiKhoan;
import com.example.duan1.database.DataHelper;

public class TaiKhoanDAO {
    DataHelper dataHelper;

    public TaiKhoanDAO(Context context) {
        dataHelper = new DataHelper(context);
    }


    public boolean checkLogin(String taikhoan, String matkhau) {
        SQLiteDatabase sqLiteOpenHelper = dataHelper.getReadableDatabase();
        Cursor cursor = sqLiteOpenHelper.rawQuery("select * from TAIKHOAN where taikhoan = ? and matkhau = ?", new String[]{taikhoan, matkhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insert(TaiKhoan obj) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put("taikhoan", obj.getTaiKhoan());
            values.put("matkhau", obj.getMatKhau());
  //          values.put("sodt",obj.getSoDT());
            long check = database.insert("TAIKHOAN", null, values);
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.d("TAIKHOANDAO insert error: ", e.getMessage());
            return false;
        }
    }

    public boolean update(String username, String oldPass, String newPass) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM TAIKHOAN WHERE taikhoan = ? and matkhau = ?", new String[]{username, oldPass});
        if (cursor.getCount() > 0) {
            ContentValues values = new ContentValues();
            values.put("matKhau", newPass);
            long check = database.update("TAIKHOAN", values, "matkhau = ?", new String[]{username});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        try {
            long check = database.delete("TAIKHOAN", "maTT = ?", new String[]{id});
            if (check == -1) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            Log.d("THUTHUDAO update error: ", e.getMessage());
            return false;
        }
    }

    public int getSoTK(String username, String password) {
        SQLiteDatabase sqLiteOpenHelper = dataHelper.getReadableDatabase();
        int soTK = 0;
        Cursor cursor = sqLiteOpenHelper.rawQuery("select * from TAIKHOAN where taikhoan = ? and matkhau = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                soTK = cursor.getInt(0);

            } while (cursor.moveToNext());

        }return soTK;
    }
}

