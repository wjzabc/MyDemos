package com.weibo.wjzabc.mydemos.views;

import com.weibo.wjzabc.mydemos.LogUtility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MyViewGroup2 extends LinearLayout {



    public MyViewGroup2(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

//    @SuppressLint("NewApi")
//    public MyViewGroup2(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        // TODO Auto-generated constructor stub
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean re=super.dispatchTouchEvent(ev);
        re=true;
        LogUtility.d(LogUtility.TAG.TouchEventDispatchActivity,""+re);
        // TODO Auto-generated method stub
        return re;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean re=super.onTouchEvent(event);
//        re=true;
        LogUtility.d(LogUtility.TAG.TouchEventDispatchActivity,""+re);
        // TODO Auto-generated method stub
        return re;
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean re=super.onInterceptTouchEvent(ev);
//        re=true;
        LogUtility.d(LogUtility.TAG.TouchEventDispatchActivity,""+re);
        return re;
    }

}
