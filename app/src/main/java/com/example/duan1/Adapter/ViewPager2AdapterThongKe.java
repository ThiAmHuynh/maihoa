package com.example.duan1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1.Fragment.ThongKe_chi_nam_Fragment;
import com.example.duan1.Fragment.ThongKe_thang_Fragment;
import com.example.duan1.Fragment.Thongke_thu_nam_Fragment;

public class ViewPager2AdapterThongKe extends FragmentStateAdapter {


    public ViewPager2AdapterThongKe(@NonNull FragmentActivity fragmentActivity) {

        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ThongKe_thang_Fragment();
            case 1:
                return new Thongke_thu_nam_Fragment();
            case 2:
                return new ThongKe_chi_nam_Fragment();

            default:
                return new ThongKe_thang_Fragment();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
