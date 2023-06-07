package com.example.duan1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1.Adapter.ViewPager2AdapterNo;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class QuanlynoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_no);
        TabLayout tabLayout = findViewById(R.id.tablayoutNo);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2No);

        ViewPager2AdapterNo adapter = new ViewPager2AdapterNo(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Tên người Nợ");
                }
                else{
                    tab.setText("Khoản nợ");
                }
            }
        }).attach();

    }
}
