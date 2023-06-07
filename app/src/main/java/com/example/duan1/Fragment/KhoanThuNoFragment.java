package com.example.duan1.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.Adapter.KhoanThuNoAdapter;
import com.example.duan1.ChiTietNoActivity;
import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.KhoanThuChi;
import com.example.duan1.Model.Loai;
import com.example.duan1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class KhoanThuNoFragment extends Fragment {
    ListView listViewKhoanThuNo;
    ArrayList<KhoanThuChi> list;
    ThuChiDAO thuChiDAO;
    ArrayList<HashMap<String,Object>> listSpinner;
    KhoanThuNoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khoanthuno,container,false);

        listViewKhoanThuNo = view.findViewById(R.id.listViewKhoanThuNo);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAddThuNo);

        thuChiDAO = new ThuChiDAO(getContext());
        getData();
        listViewKhoanThuNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChiTietNoActivity.class);
                int makhoan = list.get(position).getMaKhoan();
                intent.putExtra("makhoan",makhoan);
                startActivity(intent);
            }
        });
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThem();
            }
        });

        return view;
    }
    private void getData(){
        list = thuChiDAO.getDSKhoanThuChi("no");
        adapter = new KhoanThuNoAdapter(list,getContext(),thuChiDAO,getDataSpinner());
        listViewKhoanThuNo.setAdapter(adapter);
    }
    private void showDialogThem(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_themkhoanthuno,null);
        Spinner spnLoaiNo = view.findViewById(R.id.spnLoaiThuNO);
        EditText edtTien = view.findViewById(R.id.edtTienKhoanNo);

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
                    Toast.makeText(getContext(), "Không được để trống số tiền", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String ngay = sdf.format(new Date());
                    HashMap<String, Object> selected = (HashMap<String, Object>) spnLoaiNo.getSelectedItem();
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
        spnLoaiNo.setAdapter(adapter);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    private ArrayList<HashMap<String,Object>> getDataSpinner(){
        ArrayList<Loai> listLoai = thuChiDAO.getDsLoaiThuChi("no");
        listSpinner = new ArrayList<>();

        for(Loai loai : listLoai){
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("maloai",loai.getMaLoai());
            hashMap.put("tenloai",loai.getTenLoai());
            listSpinner.add(hashMap);
        }
        return listSpinner;
    }
}
