package com.example.duan1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1.Adapter.ViewPagerAdapterKeHoach;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class KeHoachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_hoach);
        TabLayout tabLayout = findViewById(R.id.tablayoutKeHoach);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2KeHoach);

        ViewPagerAdapterKeHoach adapter = new ViewPagerAdapterKeHoach(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Loại Kế Hoach");
                }
                else{
                    tab.setText("Khoản Kế Hoach");
                }
            }
        }).attach();

    }
}