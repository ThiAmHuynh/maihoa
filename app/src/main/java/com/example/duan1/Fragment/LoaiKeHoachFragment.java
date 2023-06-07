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

import com.example.duan1.Adapter.LoaiKeHoachAdapter;
import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.Loai;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiKeHoachFragment extends Fragment {
    ListView listViewLoaiKeHoach;
    FloatingActionButton floatAddKeHoach;
    LoaiKeHoachAdapter loaiKeHoachAdapter;
    ArrayList<Loai> list;
    ThuChiDAO thuChiDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaikehoach,container,false);

        listViewLoaiKeHoach = view.findViewById(R.id.listViewLoaiKeHoach);
        floatAddKeHoach = view.findViewById(R.id.floatAddKeHoach);
        thuChiDAO = new ThuChiDAO(getContext());
        loadData();

        floatAddKeHoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaLogThem();
            }
        });
        return view;
    }
    //    private int soTK(){
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THUTHU", Context.MODE_PRIVATE);
//        int soTK = sharedPreferences.getInt("soTK", 0);
//        return soTK;
//    }
    private void loadData(){
        list = thuChiDAO.getDsLoaiThuChi("kehoach");

        loaiKeHoachAdapter = new LoaiKeHoachAdapter(list,getContext(),thuChiDAO);
        listViewLoaiKeHoach.setAdapter(loaiKeHoachAdapter);
    }
    private void showDiaLogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themloaikehoach,null);
        EditText edtInput = view.findViewById(R.id.edtInput);
        builder.setView(view);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tenloai = edtInput.getText().toString();
                if (tenloai.length() == 0) {
                    Toast.makeText(getContext(), "Tên loại không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    Loai loaiThem = new Loai(tenloai, "kehoach");
                    if (thuChiDAO.addLoaiThuChi(loaiThem)) {
                        Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        loadData();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

