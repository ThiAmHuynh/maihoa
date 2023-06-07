package com.example.duan1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1.Fragment.KhoanChiFragment;
import com.example.duan1.Fragment.KhoanThuFragment;
import com.example.duan1.Fragment.LoaiChiFragment;
import com.example.duan1.Fragment.LoaiThuFragment;

import com.example.duan1.Fragment.ThongKe_thang_Fragment;

public class ViewPager2AdapterChi extends FragmentStateAdapter {
    public ViewPager2AdapterChi(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoaiChiFragment();
            case 1:
                return new KhoanChiFragment();
            default:
                return new LoaiChiFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
