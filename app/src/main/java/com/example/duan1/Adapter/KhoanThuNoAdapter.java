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

public class KhoanThuNoAdapter extends BaseAdapter {
    private ArrayList<KhoanThuChi> list;
    private Context context;
    private ThuChiDAO thuChiDAO;
    private ArrayList<HashMap<String, Object>> listSpinner;

    public KhoanThuNoAdapter(ArrayList<KhoanThuChi> list, Context context, ThuChiDAO thuChiDAO, ArrayList<HashMap<String, Object>> listSpinner) {
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

    public static class ViewOfItem {
        TextView txtTenThuNo, txtTienThuNo,txtTienDaTra,txtTienConThieu;
        ImageView ivThemTienNo, ivGiamTienNo;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        ViewOfItem viewOfItem;
        if (view == null) {
            view = inflater.inflate(R.layout.item_khoanno, viewGroup, false);
            viewOfItem = new ViewOfItem();
            viewOfItem.txtTenThuNo = view.findViewById(R.id.txtTenThuNo);
            viewOfItem.txtTienThuNo = view.findViewById(R.id.txtTienThuNo);
            viewOfItem.txtTienDaTra = view.findViewById(R.id.txtTienDaTra);
            viewOfItem.txtTienConThieu = view.findViewById(R.id.txtTienConThieu);
            viewOfItem.ivThemTienNo = view.findViewById(R.id.ivThemTienNo);
            viewOfItem.ivGiamTienNo = view.findViewById(R.id.ivGiamTienNo);

            view.setTag(viewOfItem);
        } else {
            viewOfItem = (ViewOfItem) view.getTag();
        }
        int sotien = list.get(i).getTien();
        int tiendatra = list.get(i).getTienDaCo();
        int tienconthieu = sotien - tiendatra;
        viewOfItem.txtTenThuNo.setText("Tên: " + list.get(i).getTenKhoan());
        viewOfItem.txtTienThuNo.setText("Số tiền: " + sotien);
        viewOfItem.txtTienDaTra.setText("Tiền đã trả: "+tiendatra);
        viewOfItem.txtTienConThieu.setText("Tiền còn thiếu: "+tienconthieu);

        viewOfItem.ivThemTienNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogThem(list.get(i));
            }
        });
        viewOfItem.ivGiamTienNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogGiam(list.get(i));
            }
        });
        return view;
    }

        private void showDialogThem(KhoanThuChi khoanThuChi){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suakhoanno,null);
        Spinner spnLoaiThuNo = view.findViewById(R.id.spnLoaiThemNo);
        EditText edtTien = view.findViewById(R.id.edtTienNo);
        builder.setView(view);

        SimpleAdapter adapter = new SimpleAdapter(
                context,
                listSpinner,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiThuNo.setAdapter(adapter);
        int index = 0;
        int vitri = -1;
        for(HashMap<String,Object> item : listSpinner){
            if((int)item.get("maloai") == khoanThuChi.getMaLoai())
                vitri = index;
            index++;
        }
        spnLoaiThuNo.setSelection(vitri);

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int tienmoi = Integer.parseInt(edtTien.getText().toString());
                int tiencu = khoanThuChi.getTien();
                int tien = tiencu + tienmoi;
                HashMap<String,Object> selected = (HashMap<String, Object>) spnLoaiThuNo.getSelectedItem();
                int maloai = (int) selected.get("maloai");
                khoanThuChi.setTien(tien);
                khoanThuChi.setMaLoai(maloai);
                if(thuChiDAO.updateKhoanThuChi(khoanThuChi)){
                    Toast.makeText(context, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    reLoadData();
                }else{
                    Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
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

    private void showDialogGiam(KhoanThuChi khoanThuChi){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suakhoanno,null);
        Spinner spnLoaiThuNo = view.findViewById(R.id.spnLoaiThemNo);
        EditText edtTien = view.findViewById(R.id.edtTienNo);
        builder.setView(view);

        SimpleAdapter adapter = new SimpleAdapter(
                context,
                listSpinner,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},
                new int[]{android.R.id.text1}
        );
        spnLoaiThuNo.setAdapter(adapter);
        int index = 0;
        int vitri = -1;
        for(HashMap<String,Object> item : listSpinner){
            if((int)item.get("maloai") == khoanThuChi.getMaLoai())
                vitri = index;
            index++;
        }
        spnLoaiThuNo.setSelection(vitri);

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int tienmoi = Integer.parseInt(edtTien.getText().toString());
                int tiencu = khoanThuChi.getTien();
                int tiendatra = khoanThuChi.getTienDaCo();
                int tiendatraupdate = tiendatra+tienmoi;
                int tien = tiencu - tienmoi;
                if (tien>0) {
                    HashMap<String, Object> selected = (HashMap<String, Object>) spnLoaiThuNo.getSelectedItem();
                    int maloai = (int) selected.get("maloai");
                    khoanThuChi.setMaLoai(maloai);
                    khoanThuChi.setTienDaCo(tiendatraupdate);
                    if (thuChiDAO.updateKhoanThuChi(khoanThuChi)) {
                        Toast.makeText(context, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                        reLoadData();
                    } else {
                        Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                    }
                }else if (tien<0){
                    Toast.makeText(context, "Tiền giảm không được quá 0", Toast.LENGTH_SHORT).show();
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
    private void reLoadData() {
        list.clear();
        list = thuChiDAO.getDSKhoanThuChi("no");
        notifyDataSetChanged();
    }
}
