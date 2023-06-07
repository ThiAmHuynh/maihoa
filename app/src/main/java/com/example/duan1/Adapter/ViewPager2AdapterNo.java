package com.example.duan1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1.Fragment.KhoanThuFragment;
import com.example.duan1.Fragment.KhoanThuNoFragment;
import com.example.duan1.Fragment.LoaiNoFragment;
import com.example.duan1.Fragment.LoaiThuFragment;

public class ViewPager2AdapterNo extends FragmentStateAdapter {

    public ViewPager2AdapterNo(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoaiNoFragment();
            case 1:
                return new KhoanThuNoFragment();
            default:
                return new LoaiNoFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}

