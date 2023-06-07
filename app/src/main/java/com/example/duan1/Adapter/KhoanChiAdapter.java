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
import com.example.duan1.Model.Loai;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class KhoanChiAdapter extends BaseAdapter {
    private ArrayList<KhoanThuChi> list;
    private Context context;
    private ThuChiDAO thuChiDAO;
    private ArrayList<HashMap<String,Object>> listSpinner1;

    public KhoanChiAdapter(ArrayList<KhoanThuChi> list, Context context, ThuChiDAO thuChiDAO,ArrayList<HashMap<String,Object>> listSpinner) {
        this.list = list;
        this.context = context;
        this.thuChiDAO = thuChiDAO;
        this.listSpinner1 = listSpinner;

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
        TextView txtTen,txtTien,txtNgayChi;
        ImageView ivSua,ivXoa;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;
        if(view == null){
            view = inflater.inflate(R.layout.item_khoanchi,viewGroup,false);
            viewOfItem = new ViewOfItem();
            viewOfItem.txtTen = view.findViewById(R.id.txtTenChi);
            viewOfItem.txtTien = view.findViewById(R.id.txtTienChi);
            viewOfItem.ivSua = view.findViewById(R.id.ivSuaChi);
            viewOfItem.ivXoa = view.findViewById(R.id.ivXoaChi);
            viewOfItem.txtNgayChi = view.findViewById(R.id.txtNgayChi);

            view.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.txtTen.setText("Tên: "+list.get(i).getTenKhoan());
        viewOfItem.txtTien.setText("Số tiền: "+list.get(i).getTien());
        viewOfItem.txtNgayChi.setText("Ngày: "+list.get(i).getNgay());

        viewOfItem.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogSua(list.get(i));
            }
        });
        viewOfItem.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int makhoan = list.get(i).getMaKhoan();
                if(thuChiDAO.deleteKhoanThuChi(makhoan)){
                    Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    reLoadData();
                }else{
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void showDialogSua(KhoanThuChi khoanThuChi){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suakhoanchi,null);
        Spinner spnLoaiThu = view.findViewById(R.id.spnSuaLoaiChi);
        EditText edtTien = view.findViewById(R.id.edtTien1);
        builder.setView(view);

        SimpleAdapter adapter = new SimpleAdapter(
                context,
                listSpinner1,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiThu.setAdapter(adapter);

        edtTien.setText(String.valueOf(khoanThuChi.getTien()));
        int index = 0;
        int vitri = -1;
        for(HashMap<String,Object> item : listSpinner1){
            if((int)item.get("maloai") == khoanThuChi.getMaLoai())
                vitri = index;
            index++;
        }
        spnLoaiThu.setSelection(vitri);

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tien = edtTien.getText().toString();
                if (tien.length() == 0) {
                    Toast.makeText(context, "Tiền không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, Object> selected = (HashMap<String, Object>) spnLoaiThu.getSelectedItem();
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
        list = thuChiDAO.getDSKhoanThuChi("chi");
        notifyDataSetChanged();
    }
}
