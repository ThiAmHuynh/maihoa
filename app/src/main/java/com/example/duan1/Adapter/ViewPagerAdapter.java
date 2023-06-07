package com.example.duan1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.duan1.Fragment.LienHe_Fragment;
import com.example.duan1.Fragment.NapTien_Fragment;
import com.example.duan1.Fragment.TaiKhoan_Fragment;
import com.example.duan1.Fragment.Trang_Chu_Fragment;


import com.example.duan1.Fragment.ThongKe_Fragment;


public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Trang_Chu_Fragment();
            case 1:
                return new ThongKe_Fragment();

            case 2:
                return new TaiKhoan_Fragment();
            case 3:
                return new LienHe_Fragment();
            default:
                return new Trang_Chu_Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
