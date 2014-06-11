
package com.weibo.wjzabc.mydemos.picture_processing;


import com.weibo.wjzabc.mydemos.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class PictureProcessingActivity extends Activity {
    ImageView Img1;
    ImageView Img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_processing);
        Img1=(ImageView) findViewById(R.id.img1);
        Img2=(ImageView) findViewById(R.id.img2);
        Bitmap srcIcon=BitmapFactory.decodeFile("/sdcard/ic_launcher.png");
        Img2.setImageBitmap(ImageUtil.createIconBitmap(this));

        Handler hand=new Handler(){
            public void handleMessage(android.os.Message msg) {
                Log.d("tttt", "Img1.getWidth()"+Img1.getWidth());//Img1.getWidth()
                Log.d("tttt", "Img2.getWidth()"+Img2.getWidth());
            };
        };
        hand.sendEmptyMessageDelayed(0, 500);
    }

}
