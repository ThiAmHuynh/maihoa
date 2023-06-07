package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.DAO.TaiKhoanDAO;
import com.example.duan1.Model.TaiKhoan;

public class Activity_Dangky extends AppCompatActivity {
    EditText edtUserdk, edtPassdk;
    Button  buttondk;
    TaiKhoan taiKhoan;
    TextView tvSoDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        edtUserdk = findViewById(R.id.edtUsername);
        edtPassdk = findViewById(R.id.edtPassword);
        buttondk = findViewById(R.id.btnDangKy);
        tvSoDT = findViewById(R.id.tvSoDT);
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO(this);
        taiKhoan = new TaiKhoan();
        String phoneNumber = getIntent().getStringExtra("phone_number");
        tvSoDT.setText(phoneNumber);

        buttondk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taikhoan = edtUserdk.getText().toString();
                String matkhau = edtPassdk.getText().toString();
                String soDT = tvSoDT.getText().toString();
                taiKhoan.setTaiKhoan(taikhoan);
                taiKhoan.setMatKhau(matkhau);
                taiKhoan.setSoDT(soDT);

                boolean checkDK = taiKhoanDAO.insert(taiKhoan);
                if(checkDK==true) {
                    Toast.makeText(Activity_Dangky.this, "thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Activity_Dangky.this, Login_Activity.class);
                    //clear activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else {
                    Toast.makeText(Activity_Dangky.this, "khong thanh cong", Toast.LENGTH_SHORT).show();
                    return;
                    }

                }
        });

    }

}

