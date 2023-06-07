package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.Adapter.AdapterChiTietNo;
import com.example.duan1.Adapter.KhoanThuNoAdapter;
import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.KhoanThuChi;
import com.example.duan1.Model.LichSu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ChiTietNoActivity extends AppCompatActivity {
    ThuChiDAO thuChiDAO;
    KhoanThuChi khoanThuChi;
    LichSu lichSu;
    ArrayList<KhoanThuChi> list;
    ArrayList<LichSu> listLS;
    Button btnTraNoCT,btnQuayLai;
    AdapterChiTietNo adapter;
    int makhoan, tien, tiendatra, tienconthieu;
    String ten;
    ListView lvLichSuNo;
    TextView txtTienConThieuCT,txtTenKhoanNoCT, txtTienNoCT, txtTienDaCoCT, txtNgayNoCT, txtmaLoaiCT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_no);
        txtTienConThieuCT = findViewById(R.id.txtTienConThieuCT);
        txtTenKhoanNoCT = findViewById(R.id.txtTenKhoanNoCT);
        txtTienNoCT = findViewById(R.id.txtTienNoCT);
        txtTienDaCoCT = findViewById(R.id.txtTienDaCoCT);
        txtNgayNoCT = findViewById(R.id.txtNgayNoCT);
        btnTraNoCT = findViewById(R.id.btnTraNoCT);
        btnQuayLai = findViewById(R.id.btnQuayLai);


        getData();

        btnTraNoCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogTraNo(list.get(0));
            }
        });
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }
    private void showDialogTraNo(KhoanThuChi khoanThuChi){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suactno,null);
        builder.setView(view);

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dialogView = (Dialog) dialog;
                EditText edtTraNo = (EditText) dialogView.findViewById(R.id.edtTraNo);
                if (edtTraNo.length() == 0) {
                    Toast.makeText(ChiTietNoActivity.this, "Không được để trống số tiền", Toast.LENGTH_SHORT).show();
                } else {
                    int tienmoi = Integer.parseInt(edtTraNo.getText().toString());
                    int tiendatraupdate = tiendatra + tienmoi;
                    int tien0 = tien - tienmoi;
                    if (tien0 > 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String ngay = sdf.format(new Date());
                        lichSu.setTen(ten);
                        lichSu.setTien(tienmoi);
                        lichSu.setNgay(ngay);
                        khoanThuChi.setMaLoai(makhoan);
                        khoanThuChi.setTienDaCo(tiendatraupdate);
                        if (thuChiDAO.updateKhoanThuChi(khoanThuChi) & thuChiDAO.addLichSu(lichSu)) {
                            Toast.makeText(ChiTietNoActivity.this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                            getData();
//                            list.clear();
//                            list = thuChiDAO.getDSchitiet("no", makhoan);
                        } else {
                            Toast.makeText(ChiTietNoActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                        }
                    } else if (tien0 < 0) {
                        Toast.makeText(ChiTietNoActivity.this, "Tiền giảm không được quá 0", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getData(){
        lvLichSuNo = findViewById(R.id.lvLichSuNo);
        thuChiDAO = new ThuChiDAO(this);
        khoanThuChi = new KhoanThuChi();
        lichSu = new LichSu();
        listLS = new ArrayList<>();
        list = new ArrayList<>();
        listLS = thuChiDAO.getDsLichSu();
        adapter = new AdapterChiTietNo(listLS,this,thuChiDAO);
        lvLichSuNo.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();
        makhoan = bundle.getInt("makhoan");
        list= thuChiDAO.getDSchitiet("no",makhoan);

        tien = list.get(0).getTien();
        tiendatra = list.get(0).getTienDaCo();
        tienconthieu = tien-tiendatra;
        ten = list.get(0).getTenKhoan();
        txtTenKhoanNoCT.setText("Tên người nợ: "+ten);
        txtTienNoCT.setText("Tiền thiếu: "+tien);
        txtTienDaCoCT.setText("Tiền đã trả: "+tiendatra);
        txtTienConThieuCT.setText("Tiền còn thiếu: "+tienconthieu);
        txtNgayNoCT.setText("Ngày: "+list.get(0).getNgay());
    }

}