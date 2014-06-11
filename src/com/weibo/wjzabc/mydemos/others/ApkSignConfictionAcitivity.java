
package com.weibo.wjzabc.mydemos.others;

import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.id;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.io.IOException;

public class ApkSignConfictionAcitivity extends Activity {
    ListView mListView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apk_sign_confiction_acitivity);
        mListView=(ListView) findViewById(R.id.listview);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        boolean same=CheckApkSignatureHelper.isSignatureSame(ApkSignConfictionAcitivity.this, 
                                "com.baidu.hao123", "/sdcard/flappybird.apk");
                        Toast.makeText(ApkSignConfictionAcitivity.this, ""+same, 0).show();
                        break;
                    case 1:
                        try {
                            //静默安装与静默卸载，需要系统权限，需要在manifest中声明相应权限。
                            Runtime.getRuntime().exec("pm install -r /sdcard/flappybird.apk");
                            Runtime.getRuntime().exec("pm uninstall com.dotgears.flappybird");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                    case 4:
                        
                        break;
                    case 5:
                        
                        break;

                    default:
                        break;
                }
                
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.apk_sign_confiction_acitivity, menu);
        return true;
    }

}
