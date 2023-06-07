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

public class LoaiKeHoachAdapter extends BaseAdapter {
    private ArrayList<Loai> list;
    private Context context;
    private ThuChiDAO thuChiDAO;

    public LoaiKeHoachAdapter(ArrayList<Loai> list, Context context, ThuChiDAO thuChiDAO) {
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
        TextView txtTenKeHoach;
        ImageView ivSuaKeHoach,ivXoaKeHoach;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        ViewOfItem viewOfItem;
        if(view == null){
            viewOfItem = new ViewOfItem();
            view = inflater.inflate(R.layout.item_loaikehoach,viewGroup,false);
            viewOfItem.txtTenKeHoach = view.findViewById(R.id.txtTenKeHoach);
            viewOfItem.ivSuaKeHoach = view.findViewById(R.id.ivSuaKeHoach);
            viewOfItem.ivXoaKeHoach = view.findViewById(R.id.ivXoaKeHoach);
            view.setTag(viewOfItem);
        }else{
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.txtTenKeHoach.setText(list.get(i).getTenLoai());

        viewOfItem.ivSuaKeHoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDiaLogSuaKeHoach(list.get(i));
            }
        });
        viewOfItem.ivXoaKeHoach.setOnClickListener(new View.OnClickListener() {
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
    private void showDiaLogSuaKeHoach(Loai loai){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sualoaikehoach,null);
        EditText edtInput = view.findViewById(R.id.edtInput);
        builder.setView(view);

        edtInput.setText(loai.getTenLoai());

        builder.setPositiveButton("Cập Nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenloai = edtInput.getText().toString();
                if (tenloai.length() == 0) {
                    Toast.makeText(context, "Tên loại không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    loai.setTenLoai(tenloai);
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
        list = thuChiDAO.getDsLoaiThuChi("kehoach");
        notifyDataSetChanged();
    }
}