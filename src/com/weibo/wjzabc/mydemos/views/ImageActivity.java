
package com.weibo.wjzabc.mydemos.views;

import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ImageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image, menu);
        return true;
    }

}
