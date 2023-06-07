package com.example.duan1.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.KhoanThuChi;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class KhoanKeHoachAdapter extends BaseAdapter {
    private ArrayList<KhoanThuChi> list;
    private Context context;
    private ThuChiDAO thuChiDAO;
    private ArrayList<HashMap<String,Object>> listSpinner;

    public KhoanKeHoachAdapter(ArrayList<KhoanThuChi> list, Context context, ThuChiDAO thuChiDAO, ArrayList<HashMap<String,Object>> listSpinner) {
        this.list = list;
        this.context = context;
        this.thuChiDAO = thuChiDAO;
        this.listSpinner = listSpinner;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewOfItem{
        TextView txtTenKeHoach,txtTienKeHoach,txtNgayKeHoach,txtGioKeHoach;
        ImageView ivSuaKeHoach,ivXoaKeHoach;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;
        if(view == null){
            view = inflater.inflate(R.layout.item_khoankehoach,viewGroup,false);
            viewOfItem = new ViewOfItem();
            viewOfItem.txtTenKeHoach = view.findViewById(R.id.txtTenKeHoach);
            viewOfItem.txtTienKeHoach = view.findViewById(R.id.txtTienKeHoach);
            viewOfItem.ivSuaKeHoach = view.findViewById(R.id.ivSuaKeHoach);
            viewOfItem.ivXoaKeHoach = view.findViewById(R.id.ivXoaKeHoach);
            viewOfItem.txtNgayKeHoach = view.findViewById(R.id.txtNgayKeHoach);


            view.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.txtTenKeHoach.setText("Tên: "+list.get(i).getTenKhoan());
        viewOfItem.txtTienKeHoach.setText("Số tiền: "+list.get(i).getTien());
        viewOfItem.txtNgayKeHoach.setText("Ngày: "+list.get(i).getNgay());



        viewOfItem.ivSuaKeHoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogSua(list.get(i));
            }
        });
        viewOfItem.ivXoaKeHoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int makhoan = list.get(i).getMaKhoan();
                if(thuChiDAO.deleteKhoanThuChi(makhoan)){
                    Toast.makeText(context, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    reLoadData();
                }else{
                    Toast.makeText(context, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void showDialogSua(KhoanThuChi khoanThuChi){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suakhoankehoach,null);
        Spinner spnLoaiKeHoach = view.findViewById(R.id.spnLoaiKeHoach);
        EditText edtTienKeHoach = view.findViewById(R.id.edtTienKeHoach);
        builder.setView(view);

        SimpleAdapter adapter = new SimpleAdapter(
                context,
                listSpinner,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiKeHoach.setAdapter(adapter);

        edtTienKeHoach.setText(String.valueOf(khoanThuChi.getTien()));
        int index = 0;
        int vitri = -1;
        for(HashMap<String,Object> item : listSpinner){
            if((int)item.get("maloai") == khoanThuChi.getMaLoai())
                vitri = index;
            index++;
        }
        spnLoaiKeHoach.setSelection(vitri);

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tien = edtTienKeHoach.getText().toString();
                if (tien.length() == 0) {
                    Toast.makeText(context, "Tiền không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, Object> selected = (HashMap<String, Object>) spnLoaiKeHoach.getSelectedItem();
                    int maloai = (int) selected.get("maloai");
                    khoanThuChi.setTien(Integer.parseInt(tien));
                    khoanThuChi.setMaLoai(maloai);
                    if (thuChiDAO.updateKhoanThuChi(khoanThuChi)) {
                        Toast.makeText(context, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        reLoadData();
                    } else {
                        Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
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
    private void reLoadData(){
        list.clear();
        list = thuChiDAO.getDSKhoanThuChi("kehoach");
        notifyDataSetChanged();
    }
}