
package com.weibo.wjzabc.mydemos.views;

import com.weibo.wjzabc.mydemos.LogUtility;
import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.zip.Inflater;

public class TouchEventDispatchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list=new ListView(this);
        String[] str=new String[]{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"};
        list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.activity_touch_event_dispatch, list, false));
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,str));
        setContentView(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.touch_event_dispatch, menu);
        return true;
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean re=super.dispatchTouchEvent(ev);
        LogUtility.d(LogUtility.TAG.TouchEventDispatchActivity,""+re);
        // TODO Auto-generated method stub
        return re;
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean re=super.onTouchEvent(event);
        LogUtility.d(LogUtility.TAG.TouchEventDispatchActivity,""+re);
        // TODO Auto-generated method stub
        return re;
    }

}
