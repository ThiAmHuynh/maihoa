package com.example.duan1.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan1.Adapter.ViewPager2AdapterThongKe;
import com.example.duan1.Adapter.ZoomOutPageTransformer;
import com.example.duan1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThongKe_Fragment extends Fragment {
    public ThongKe_Fragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = view.findViewById(R.id.tablayoutThongKe);
        ViewPager2 viewPager = view.findViewById(R.id.viewpager2ThongKe);
        ViewPager2AdapterThongKe adapter = new ViewPager2AdapterThongKe(getActivity());
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Thống kê theo tháng");
                    break;
                case 1:
                    tab.setText("Thống kê thu theo năm");
                    break;
                case 2:
                    tab.setText("Thống kê chi theo năm");
                    break;
            }
        }).attach();
    }
}