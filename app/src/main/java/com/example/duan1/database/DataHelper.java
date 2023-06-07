package com.example.duan1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    public DataHelper( Context context) {
        super(context, "ThuChiDatabase", null, 33);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qTaiKhoan = "CREATE TABLE TAIKHOAN (soTK integer primary key autoincrement, taikhoan text, matkhau text)";
        db.execSQL(qTaiKhoan);
        String qloai = "CREATE TABLE LOAI (maloai integer primary key autoincrement, tenloai text, trangthai text, soTK integer,FOREIGN KEY(soTK) references TAIKHOAN(SoTK))";
        db.execSQL(qloai);
        String qKhoan = "CREATE TABLE KHOANTHUCHI(makhoan integer primary key autoincrement, tien integer, tiendaco integer, ngay text, maloai integer,FOREIGN KEY(maloai) references LOAI(maloai))";
        db.execSQL(qKhoan);
        //data mẫu:
        String taikhoan = "INSERT INTO TAIKHOAN VALUES(1, 'admin', 'admin'), (2, 'hoa123', '123'), (3, 'tai456', '456'), (4, 'loi789', '789')";
        db.execSQL(taikhoan);

        String loai = "INSERT INTO LOAI VALUES(1, 'tiền xăng', 'chi',2), (2, 'tiền trúng sô', 'thu',2), (3, 'ăn sáng', 'chi',2)";
        db.execSQL(loai);

        String loai1 = "INSERT INTO LOAI VALUES(4, 'tiền ăn', 'chi',3), (5, 'tiền lương', 'thu',3), (6, 'tiền lãi', 'thu',3)";
        db.execSQL(loai1);

        String khoanthuchi = "INSERT INTO KHOANTHUCHI VALUES(1, 6000,0, '02/11/2022' ,1), (2, 5000,0, '03/11/2022', 3), (3, 10000,0, '05/01/2022',2)";
        db.execSQL(khoanthuchi);

        String khoanthuchi1 = "INSERT INTO KHOANTHUCHI VALUES(4, 5000,0, '01/11/2022' ,6), (5, 7000,0, '09/11/2022', 5), (6, 1000,0, '08/12/2022',4)";
        db.execSQL(khoanthuchi1);

        String lichsu = "create table LICHSU (mals integer primary key, ten string, tien integer, ngay string)";
        db.execSQL(lichsu);
//        String khoanthuchi10 = "INSERT INTO LICHSU VALUES(1,'hoa', 5000,'01/11/2022'), (2,'hoa', 7000,'09/11/2022'), (3,'hoa', 1000,'08/12/2022')";
//        db.execSQL(khoanthuchi10);

           }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            String dTaiKhoan = "DROP TABLE IF EXISTS TAIKHOAN";
            db.execSQL(dTaiKhoan);
            String dLoai = "DROP TABLE IF EXISTS LOAI";
            db.execSQL(dLoai);
            String dKhoan = "DROP TABLE IF EXISTS KHOANTHUCHI";
            db.execSQL(dKhoan);
            String lichsu = "DROP TABLE IF EXISTS LICHSU";
            db.execSQL(lichsu);
            onCreate(db);

        }
    }
}
