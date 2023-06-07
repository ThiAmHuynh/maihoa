package com.example.duan1.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.duan1.ChiActivity;
import com.example.duan1.KeHoachActivity;
import com.example.duan1.QuanlynoActivity;
import com.example.duan1.R;
import com.example.duan1.ThuActivity;


public class Trang_Chu_Fragment extends Fragment {
Button btn;
    public Trang_Chu_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_trang_chu_, container, false);
        btn = view.findViewById(R.id.btnThu);
         btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThuActivity.class);
                startActivity(intent);
            }
        });
        btn = view.findViewById(R.id.btnChi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChiActivity.class);
                startActivity(intent);
            }
        });
        btn = view.findViewById(R.id.btnQLKH);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KeHoachActivity.class);
                startActivity(intent);
            }
        });
        btn = view.findViewById(R.id.btnQLNO);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuanlynoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}