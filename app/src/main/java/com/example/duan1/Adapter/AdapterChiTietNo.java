package com.example.duan1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.Model.KhoanThuChi;
import com.example.duan1.Model.LichSu;
import com.example.duan1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterChiTietNo extends BaseAdapter {
    private ArrayList<LichSu> list;
    private Context context;
    private ThuChiDAO thuChiDAO;

    public AdapterChiTietNo(ArrayList<LichSu> list, Context context, ThuChiDAO thuChiDAO) {
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
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewOfItem {
        TextView txtTenLichSu, txtTienLichSu,txtNgayLichSu;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        ViewOfItem viewOfItem;
        if (view == null) {
            view = inflater.inflate(R.layout.item_ctno, viewGroup, false);
            viewOfItem = new ViewOfItem();
            viewOfItem.txtTenLichSu = view.findViewById(R.id.txtTenLichSu);
            viewOfItem.txtTienLichSu = view.findViewById(R.id.txtTienLichSu);
            viewOfItem.txtNgayLichSu = view.findViewById(R.id.txtNgayLichSu);

            view.setTag(viewOfItem);
        } else {
            viewOfItem = (ViewOfItem) view.getTag();
        }
        viewOfItem.txtTenLichSu.setText("Tên: " + list.get(i).getTen());
        viewOfItem.txtTienLichSu.setText("Số tiền: " + list.get(i).getTien());
        viewOfItem.txtNgayLichSu.setText("Ngày trả: "+list.get(i).getNgay());

        return view;
    }

}
