package com.example.duan1.Adapter;

import android.content.AttributionSource;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class CustomViewpager2 extends ViewPager{
    private boolean enable;

    public CustomViewpager2(@NonNull Context context, AttributeSet attributeSet ) {
        super(context, attributeSet);
        this.enable =true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.enable){
            return super.onTouchEvent(event);
        }
        return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        if (this.enable){
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }
    public void setPagingEnabled(boolean enable){
        this.enable = enable;
    }
}
