package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.duan1.Adapter.ViewPagerAdapter;
import com.example.duan1.Adapter.ZoomOutPageTransformer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

       ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                    bottomNavigationView.getMenu().findItem(R.id.mTrangChu).setChecked(true);
                    break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.mThongKe).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.mTaiKhoan).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.mLienHe).setChecked(true);
                        break;
                }
            }
        });
            bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
                @Override
                public void onNavigationItemReselected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mTrangChu:
                viewPager.setCurrentItem(0);
                break;
            case R.id.mThongKe:
                viewPager.setCurrentItem(1);
                break;
            case R.id.mTaiKhoan:
                viewPager.setCurrentItem(2);
                break;
            case R.id.mLienHe:
                viewPager.setCurrentItem(3);
                break;
                    }
                }
            });

    }
}