
package com.weibo.wjzabc.mydemos.others;

import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class Dp_px_Activity extends Activity {

    private DisplayMetrics met;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dp_px_);
        met=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(met);
        float height=getResources().getDimension(R.dimen.dp_height);
        Log.d("aaaa", "height="+height);
//        findViewById(R.id.px).setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,(int)(height*met.density)));
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dp_px_, menu);
        return true;
    }
    
@Override
protected void onResume() {
    // TODO Auto-generated method stub
    super.onResume();
    
    
}

}
