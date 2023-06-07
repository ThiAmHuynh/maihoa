package com.example.duan1.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.DAO.TaiKhoanDAO;
import com.example.duan1.Login_Activity;
import com.example.duan1.R;

public class TaiKhoan_Fragment extends Fragment {
    TextView txtSoTK, txtTenTaiKhoan, txtDoiMatKhau, txtDangXuat;

    public TaiKhoan_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tai_khoan_, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTenTaiKhoan = view.findViewById(R.id.txtTenTaiKhoan);
        txtSoTK = view.findViewById(R.id.txtSoTK);
        txtDoiMatKhau = view.findViewById(R.id.txtDoiMatKhau);
        txtDangXuat = view.findViewById(R.id.txtDangXuat);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THUTHU", Context.MODE_PRIVATE);
        String taikhoan = sharedPreferences.getString("taikhoan", "");
        int soTK = sharedPreferences.getInt("soTK",0);
        txtSoTK.setText("Số tài khoản:"+soTK);
        txtTenTaiKhoan.setText("Tên tài khoản: "+taikhoan);
        txtDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showDialogDoiMatKhau();
            }
        });
        txtDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Login_Activity.class);
                //clear activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
    private void showDialogDoiMatKhau() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setNegativeButton("Cập nhật", null)
                .setPositiveButton("Huỷ", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        EditText edtPassOld = view.findViewById(R.id.edtPassOld);
        EditText edtPassNew = view.findViewById(R.id.edtPassNew);
        EditText edtPassNewAgain = view.findViewById(R.id.edtPassNewAgain);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtPassOld.getText().toString();
                String newPass = edtPassNew.getText().toString();
                String newPassAgain = edtPassNewAgain.getText().toString();
                if (oldPass.equals("") | newPass.equals("")) {
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPass.equals(newPassAgain)) {
                        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(getContext());
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THUTHU", Context.MODE_PRIVATE);
                        String taikhoan = sharedPreferences.getString("taikhoan", "");
                        boolean check = taiKhoanDAO.update(taikhoan, oldPass, newPass);
                        if (check) {
                            Toast.makeText(getContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), Login_Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}