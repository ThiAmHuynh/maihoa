package com.example.duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.duan1.Adapter.ViewPager2AdapterThu;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_thu);
        TabLayout tabLayout = findViewById(R.id.tablayoutThu);
        ViewPager2 viewPager2 = findViewById(R.id.viewpager2Thu);

        ViewPager2AdapterThu adapter = new ViewPager2AdapterThu(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0){
                    tab.setText("Loại Thu");
                }
                else{
                    tab.setText("Khoản Thu");
                }
            }
        }).attach();

    }
}