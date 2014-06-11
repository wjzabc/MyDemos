
package com.weibo.wjzabc.mydemos.performance;

import com.weibo.wjzabc.mydemos.LogUtility;
import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.view.Menu;

import java.util.concurrent.Executor;

public class Traceview extends Activity {
    private boolean isComputing=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traceview);
    }
    
    private void whileforever(){
        while (isComputing) {
        }
    }
   
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.traceview, menu);
        return true;
    }
    
    @Override
    public void onBackPressed() {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                isComputing=false;
                LogUtility.d(LogUtility.TAG.Traceview,"handleMessage isComputing="+isComputing);
                return null;
            }
            
        }.execute();
        for(int i=0;i<50;i++){
            new Thread("myThread"+i) {
                @Override
                public void run() {
                    whileforever();
                }
            }.start();
//            new AsyncTask<Void, Void, Void>(){
//
//                @Override
//                protected Void doInBackground(Void... params) {
//                    whileforever();
//                    return null;
//                }
//                
//            }.execute();
        }

        whileforever();
        finish();
//        new Thread(){
//            @Override
//            public void run() {
//                Looper.prepare();
//                Looper.myLooper().loop();
//                Handler hand=new Handler(){
//                    public void handleMessage(android.os.Message msg) {
//                        isComputing=false;
//                        LogUtility.d(LogUtility.TAG.Traceview,"handleMessage isComputing="+isComputing);
//                        Looper.myLooper().quit();
//                    };
//                };
//                hand.sendEmptyMessageDelayed(0, 3000);
//            }
//        }.start();
//        Handler hand=new Handler(){
//            public void handleMessage(android.os.Message msg) {
//                isComputing=true;
//                long i=0;
//                while(true){
//                    i++;
////                    LogUtility.d(LogUtility.TAG.Traceview,"times="+i);
//                    if(!isComputing){
//                        break;
//                    }
//                }
//                finish();
//            };
//        };
//        hand.sendEmptyMessage(0);
    }

}
