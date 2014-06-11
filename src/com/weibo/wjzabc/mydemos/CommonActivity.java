
package com.weibo.wjzabc.mydemos;

import com.weibo.wjzabc.mydemos.Exceptions.ListViewIndexOutOfBoundsException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 *
 */
public abstract class CommonActivity extends Activity {
    private static final int NUM = 6;

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout=new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        layout.setBackgroundColor(Color.RED);
       
        for(int i=1;i<NUM;i++){
            Button b=new Button(this);
            setupButton(b,i);
            layout.addView(b);
        }
        setContentView(layout);
    }

    protected abstract void setupButton(Button b,int index);
}
