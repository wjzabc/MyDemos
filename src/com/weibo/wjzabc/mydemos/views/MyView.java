package com.weibo.wjzabc.mydemos.views;

import com.weibo.wjzabc.mydemos.LogUtility;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyView extends TextView {

    public MyView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean re=super.dispatchTouchEvent(ev);
        LogUtility.d(LogUtility.TAG.TouchEventDispatchActivity,""+re);
        // TODO Auto-generated method stub
        return re;
    }
    
//    @Override
//    protected void onDraw(Canvas canvas) {
//        // TODO Auto-generated method stub
//        super.onDraw(canvas);
//        canvas.drawARGB(255, 255, 0, 0);
//    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean re=super.onTouchEvent(event);
        LogUtility.d(LogUtility.TAG.TouchEventDispatchActivity,""+re);
        // TODO Auto-generated method stub
        return re;
    }
    
    private void onInterceptTouchEvent() {
        // TODO Auto-generated method stub

    }
    
}
