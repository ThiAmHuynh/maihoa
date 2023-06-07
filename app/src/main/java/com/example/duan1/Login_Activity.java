package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1.DAO.TaiKhoanDAO;
import com.example.duan1.VerifyPhoneNumber.FingeprintActivity;
import com.example.duan1.VerifyPhoneNumber.VerifyPhoneActivity;

public class Login_Activity extends AppCompatActivity {
    TaiKhoanDAO taiKhoanDAO;
    CheckBox chkRememberPass;
    EditText edtUser, edtPass;
    Button bntLogin,bntdangky,btnLoginOTP,btnLoginFinger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        edtUser = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPassword);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        bntLogin = findViewById(R.id.btnLogin);
        bntdangky = findViewById(R.id.btnDANGKY);
        btnLoginOTP = findViewById(R.id.btnLoginOTP);
        btnLoginFinger = findViewById(R.id.btnLoginFinger);

        taiKhoanDAO = new TaiKhoanDAO(this);
        final int[] soTK = new int[1];
        showInfoRem();

        bntdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login_Activity.this, "dd", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login_Activity.this, Activity_Dangky.class);
                startActivity(intent);
            }
        });

        bntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtUser.getText().toString();
                String matkhau = edtPass.getText().toString();
                if (validate(taikhoan, matkhau) == true) {
                    soTK[0] = taiKhoanDAO.getSoTK(taikhoan,matkhau);
                    Intent intent = new Intent(Login_Activity.this, MainActivity.class);
                    saveInfo(soTK[0]);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        });

        btnLoginOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, VerifyPhoneActivity.class);
                startActivity(intent);
            }
        });

        btnLoginFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, FingeprintActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showInfoRem() {

        SharedPreferences sharedPreferences = getSharedPreferences("THUTHU", MODE_PRIVATE);
        String taikhoan = sharedPreferences.getString("taikhoan", "");
        String matkhau = sharedPreferences.getString("matkhau", "");
        boolean check = sharedPreferences.getBoolean("remember", false);
        if (check){
            edtUser.setText(taikhoan);
            edtPass.setText(matkhau);
            chkRememberPass.setChecked(check);
        }else {
            edtUser.setText("");
            edtPass.setText("");
            chkRememberPass.setChecked(check);
        }

    }

    public void saveInfo(Integer soTK) {
        SharedPreferences sharedPreferences = getSharedPreferences("THUTHU", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean ischeck = chkRememberPass.isChecked();
        editor.putInt("soTK",soTK);
        editor.putString("taikhoan", edtUser.getText().toString());
        editor.putString("matkhau", edtPass.getText().toString());
        editor.putBoolean("remember", ischeck);
        if (ischeck) {

        } else {
            editor.clear();
        }
        editor.commit();
    }

    public boolean validate(String username, String password) {
        boolean check = taiKhoanDAO.checkLogin(username, password);
        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "nhap ten dang nhap hoac mat khau", Toast.LENGTH_SHORT).show();
            return false;
        } else if (check == false) {
            return false;
        }
        return true;
    }
}
