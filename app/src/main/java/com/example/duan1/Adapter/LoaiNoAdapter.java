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
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.Loai;
import com.example.duan1.R;

import java.util.ArrayList;

public class LoaiNoAdapter extends BaseAdapter {
    private ArrayList<Loai> list;
    private Context context;
    private ThuChiDAO thuChiDAO;

    public LoaiNoAdapter(ArrayList<Loai> list, Context context, ThuChiDAO thuChiDAO) {
        this.list = list;
        this.context = context;
        this.thuChiDAO = thuChiDAO;
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
        TextView txtTenKhoanNo;
        ImageView ivSuaKhoanNo,ivXoaKhoanNo;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;
        if(view == null){
            viewOfItem = new ViewOfItem();
            view = inflater.inflate(R.layout.item_loaino,viewGroup,false);
            viewOfItem.txtTenKhoanNo = view.findViewById(R.id.txtTenKhoanNo);
            viewOfItem.ivSuaKhoanNo = view.findViewById(R.id.ivSuaKhoanNo);
            viewOfItem.ivXoaKhoanNo = view.findViewById(R.id.ivXoaKhoanNo);
            view.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.txtTenKhoanNo.setText(list.get(i).getTenLoai());
        viewOfItem.ivSuaKhoanNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDiaLogSuaLoaiThuNo(list.get(i));
            }
        });
        viewOfItem.ivXoaKhoanNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idCanXoa = list.get(i).getMaLoai();
                if(thuChiDAO.deleteLoaiThuChi(idCanXoa)){
                    Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    reLoadData();
                }else{
                    Toast.makeText(context, "that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    private void showDiaLogSuaLoaiThuNo(Loai loai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sualoaino,null);
        EditText edtTenno = view.findViewById(R.id.edt_sua_ten_khoan_no);
        builder.setView(view);

        edtTenno.setText(loai.getTenLoai());

        builder.setPositiveButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenno = edtTenno.getText().toString();
                if (tenno.length() == 0) {
                    Toast.makeText(context, "Tên loại không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    loai.setTenLoai(tenno);
                    if (thuChiDAO.updateLoaiThuChi(loai)) {
                        Toast.makeText(context, "cap nhat thnah cong", Toast.LENGTH_SHORT).show();
                        reLoadData();
                    } else {
                        Toast.makeText(context, "Thát bai", Toast.LENGTH_SHORT).show();
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
    private void reLoadData(){
        list.clear();
        list = thuChiDAO.getDsLoaiThuChi("no");
        notifyDataSetChanged();
    }
}
