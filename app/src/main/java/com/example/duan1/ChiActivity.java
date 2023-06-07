package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.duan1.Adapter.ViewPager2AdapterChi;
import com.example.duan1.Adapter.ViewPager2AdapterThu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ChiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_chi);
        TabLayout tabLayout = findViewById(R.id.tablayoutChi);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2Chi);

        ViewPager2AdapterChi adapter = new ViewPager2AdapterChi(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Loại Chi");
                }
                else{
                    tab.setText("Khoản Chi");
                }
            }
        }).attach();

    }
}