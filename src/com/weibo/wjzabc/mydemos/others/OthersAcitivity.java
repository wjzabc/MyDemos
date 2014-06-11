
package com.weibo.wjzabc.mydemos.others;

import com.weibo.wjzabc.mydemos.LogUtility;
import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.id;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.io.IOException;
import java.lang.reflect.Field;

public class OthersAcitivity extends Activity {
    ListView mListView=null;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);
        mListView=(ListView) findViewById(R.id.listview);
        context=this;
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        try {
                            //静默安装与静默卸载，需要系统权限，需要在manifest中声明相应权限。
                            Runtime.getRuntime().exec("pm install -r /sdcard/flappybird.apk");
                            Runtime.getRuntime().exec("pm uninstall com.dotgears.flappybird");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        TelephonyManager tm = (TelephonyManager) OthersAcitivity.this.getSystemService(TELEPHONY_SERVICE);
                        String type=null;
                        switch (tm.getNetworkType()) {
                            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                                type="Network type is unknown";
                                break;
                            case TelephonyManager.NETWORK_TYPE_GPRS:
                                type="Network type is GPRS";
                                break;
                                
                            case TelephonyManager.NETWORK_TYPE_EDGE:
                                type="Network type is EDGE";
                                break;
                            case TelephonyManager.NETWORK_TYPE_UMTS:
                                type="Network type is UMTS";
                                break;
                            case TelephonyManager.NETWORK_TYPE_CDMA:
                                type="Network type is CDMA: Either IS95A or IS95B";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                                type="Network type is EVDO revision 0";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                                type="Network type is EVDO revision A";
                                break;
                            case TelephonyManager.NETWORK_TYPE_1xRTT:
                                type="Network type is 1xRTT";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSDPA:
                                type="Network type is HSDPA";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSUPA:
                                type="Network type is HSUPA";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSPA:
                                type="Network type is HSPA";
                                break;
                            case TelephonyManager.NETWORK_TYPE_IDEN:
                                type="Network type is iDen";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                                type="Network type is EVDO revision B";
                                break;
                            case TelephonyManager.NETWORK_TYPE_LTE:
                                type="Network type is LTE";
                                break;
                            case TelephonyManager.NETWORK_TYPE_EHRPD:
                                type="Current network is eHRPD";
                                break;
                            case TelephonyManager.NETWORK_TYPE_HSPAP:
                                type="Current network is HSPA+";
                                break;
                        }
                        Toast.makeText(context, type, 0).show();
                        break;
                    case 2:
                        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo info = connectivity.getActiveNetworkInfo();
//                        NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);
                        if( info!=null){
//                            String state=null;
//                            if(info.getState()==NetworkInfo.State.CONNECTED) {
//                            } else if (info.getState() == NetworkInfo.State.CONNECTING) {
//                                state=info.getState().name();
//                            } else if (info.getState() == NetworkInfo.State.SUSPENDED) {
//
//                            } else if (info.getState() == NetworkInfo.State.DISCONNECTING) {
//
//                            } else if (info.getState() == NetworkInfo.State.DISCONNECTED) {
//
//                            } else if (info.getState() == NetworkInfo.State.UNKNOWN) {
//
//                            }
                            Toast.makeText(context, ""+info.getTypeName()+" "+info.getState().name(),0).show();
                        }else{
                            Toast.makeText(context, "getActiveNetworkInfo()==null",0).show();
                        }
                        break;
                    case 3://反射获取类静态属性
                        try {
                            Class ownerClass = Class.forName("android.provider.Downloads$Impl");
                            LogUtility.d(ownerClass.toString());
                            Field field = ownerClass.getField("CONTENT_URI");
                            LogUtility.d(field.toString());
                            Object property = field.get(ownerClass);
                            LogUtility.d(property.toString());
                            //resolver.delete(android.provider.Downloads.Impl.CONTENT_URI, null, null);
                        } catch (ClassNotFoundException e) {
                            LogUtility.d("ClassNotFoundException");
                            e.printStackTrace();
                        } catch (NoSuchFieldException e) {
                            LogUtility.d("NoSuchFieldException");
                            e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        break;
                    case 4://<item >send com.xxx.xxx 广播</item>
                        sendBroadcast(new Intent("com.xxx.xxx"));
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
