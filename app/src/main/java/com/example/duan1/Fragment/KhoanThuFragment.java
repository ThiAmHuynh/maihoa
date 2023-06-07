package com.example.duan1.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.Adapter.KhoanThuAdapter;
import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.KhoanThuChi;
import com.example.duan1.Model.Loai;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class KhoanThuFragment extends Fragment {
    ListView listViewKhoanThu;
    ArrayList<KhoanThuChi> list;
    ThuChiDAO thuChiDAO;
    ArrayList<HashMap<String,Object>> listSpinner;
    KhoanThuAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanthu,container,false);

        listViewKhoanThu = view.findViewById(R.id.listViewKhoanThu);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAddThu);

        thuChiDAO = new ThuChiDAO(getContext());
        getData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThem();
            }
        });

        return view;
    }
    private void getData(){
        list = thuChiDAO.getDSKhoanThuChi("thu");
        adapter = new KhoanThuAdapter(list,getContext(),thuChiDAO,getDataSpinner());
        listViewKhoanThu.setAdapter(adapter);
    }
    private void showDialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_themkhoanthu,null);
        Spinner spnLoaiThu = view.findViewById(R.id.spnLoaiThu);
        EditText edtTien = view.findViewById(R.id.edtTien);

        builder.setView(view);

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                getDataSpinner(),
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String tien = edtTien.getText().toString();
                if (tien.length() == 0) {
                    Toast.makeText(getContext(), "Tiền không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String ngay = sdf.format(new Date());
                    HashMap<String, Object> selected = (HashMap<String, Object>) spnLoaiThu.getSelectedItem();
                    int maloai = (int) selected.get("maloai");
                    KhoanThuChi khoanThuChi = new KhoanThuChi(Integer.parseInt(tien), maloai, ngay);
                    if (thuChiDAO.addKhoanThuChi(khoanThuChi)) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        getData();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        spnLoaiThu.setAdapter(adapter);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private ArrayList<HashMap<String,Object>> getDataSpinner(){
        ArrayList<Loai> listLoai = thuChiDAO.getDsLoaiThuChi("thu");
        listSpinner = new ArrayList<>();

        for(Loai loai : listLoai){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("maloai",loai.getMaLoai());
            hashMap.put("tenloai",loai.getTenLoai());
            listSpinner.add(hashMap);
        }
        return listSpinner;
    }
//    private int soTK(){
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THUTHU", Context.MODE_PRIVATE);
//        int soTK = sharedPreferences.getInt("soTK", 0);
//        return soTK;
//    }
}
