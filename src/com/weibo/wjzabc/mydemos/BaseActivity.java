
package com.weibo.wjzabc.mydemos;

import com.weibo.wjzabc.mydemos.Exceptions.ListViewIndexOutOfBoundsException;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 *
 */
public class BaseActivity extends Activity {

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class c = this.getClass();
        LogUtility.d(LogUtility.TAG.BaseActivity,"c.getName()="+c.getName());//打印子类名
    }
}
