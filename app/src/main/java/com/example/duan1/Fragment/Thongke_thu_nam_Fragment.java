package com.example.duan1.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.DAO.ThuChiDAO;
import com.example.duan1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Thongke_thu_nam_Fragment extends Fragment {
    ThuChiDAO thuChiDAO;

    public Thongke_thu_nam_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_thu_nam, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BarChart barChart = view.findViewById(R.id.chartThongKeThuTheoNam);
        thuChiDAO = new ThuChiDAO(getContext());
        ArrayList<BarEntry> thongKeNam = new ArrayList<>();
//        Integer[] yData = thuChiDAO.getDoanhThuTheoThang(2, "2022/01/01", "2022/01/31");
//
//        thongKeNam.add(new BarEntry(1, yData[0]));
//        thongKeNam.add(new BarEntry(1, yData[1]));
        thongKeNam.add(new BarEntry(1,thuChiDAO.getDoanhThuNam("2022/01/01","2022/01/31","thu")));
        thongKeNam.add(new BarEntry(2,thuChiDAO.getDoanhThuNam("2022/02/01","2022/02/28","thu")));
        thongKeNam.add(new BarEntry(3,thuChiDAO.getDoanhThuNam("2022/03/01","2022/03/31","thu")));
        thongKeNam.add(new BarEntry(4,thuChiDAO.getDoanhThuNam("2022/04/01","2022/04/30","thu")));
        thongKeNam.add(new BarEntry(5,thuChiDAO.getDoanhThuNam("2022/05/01","2022/05/31","thu")));
        thongKeNam.add(new BarEntry(6,thuChiDAO.getDoanhThuNam("2022/06/01","2022/06/30","thu")));
        thongKeNam.add(new BarEntry(7,thuChiDAO.getDoanhThuNam("2022/07/01","2022/07/31","thu")));
        thongKeNam.add(new BarEntry(8,thuChiDAO.getDoanhThuNam("2022/08/01","2022/08/31","thu")));
        thongKeNam.add(new BarEntry(9,thuChiDAO.getDoanhThuNam("2022/09/01","2022/09/30","thu")));
        thongKeNam.add(new BarEntry(10,thuChiDAO.getDoanhThuNam("2022/10/01","2022/10/31","thu")));
        thongKeNam.add(new BarEntry(11,thuChiDAO.getDoanhThuNam("2022/11/01","2022/11/30","thu")));
        thongKeNam.add(new BarEntry(12,thuChiDAO.getDoanhThuNam("2022/12/01","2022/12/31","thu")));
        BarDataSet barDataSet = new BarDataSet(thongKeNam, "Thống kê theo năm");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(10f);
        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Tiền");
        barChart.animateY(2000);
    }
//    private int soTK(){
//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("THUTHU", Context.MODE_PRIVATE);
//        int soTK = sharedPreferences.getInt("soTK", 0);
//        return soTK;
//    }
}
