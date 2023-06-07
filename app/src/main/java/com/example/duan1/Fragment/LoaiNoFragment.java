package com.example.duan1.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.Adapter.LoaiNoAdapter;
import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.KhoanThuChi;
import com.example.duan1.Model.Loai;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiNoFragment extends Fragment {
    ListView listViewLoaiThuNo;
    FloatingActionButton floatAddLoaiNo;
    LoaiNoAdapter loaiNoAdapter;
    ArrayList<Loai> list;
    ThuChiDAO thuChiDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaino, container, false);

        listViewLoaiThuNo = view.findViewById(R.id.listViewLoaiThuNo);
        floatAddLoaiNo = view.findViewById(R.id.floatAddLoaiNo);

        thuChiDAO = new ThuChiDAO(getContext());
        loadData();

        floatAddLoaiNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogThem();
            }
        });
        return view;
    }

    private void loadData() {
        list = thuChiDAO.getDsLoaiThuChi("no");

        loaiNoAdapter = new LoaiNoAdapter(list, getContext(), thuChiDAO);
        listViewLoaiThuNo.setAdapter(loaiNoAdapter);
    }

    private void showDiaLogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themloaino, null);
        EditText edtTen = view.findViewById(R.id.edtTenNo);

        builder.setView(view);

        builder.setPositiveButton("Them", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String ten = edtTen.getText().toString();
                if (ten.equals("")) {
                    Toast.makeText(getContext(), "Tên không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    Loai loai = new Loai(ten, "no");
                    if (thuChiDAO.addLoaiThuChi(loai)) {
                        Toast.makeText(getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                        loadData();
                    } else {
                        Toast.makeText(getContext(), "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setNegativeButton("huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}