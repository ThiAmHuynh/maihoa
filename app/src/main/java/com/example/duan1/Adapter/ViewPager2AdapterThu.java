package com.example.duan1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1.Fragment.KhoanThuFragment;
import com.example.duan1.Fragment.LoaiThuFragment;
import com.example.duan1.Fragment.ThongKe_thang_Fragment;
import com.example.duan1.Model.Loai;

public class ViewPager2AdapterThu extends FragmentStateAdapter {

    public ViewPager2AdapterThu(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LoaiThuFragment();
            case 1:
                return new KhoanThuFragment();
            default:
                return new LoaiThuFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
