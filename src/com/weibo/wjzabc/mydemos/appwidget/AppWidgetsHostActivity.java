
package com.weibo.wjzabc.mydemos.appwidget;

import com.weibo.wjzabc.mydemos.R;
import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.List;

public class AppWidgetsHostActivity extends Activity {

    private LinearLayout mHost;
    private AppWidgetHost mWidgetHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_widgets_host);
        mHost=(LinearLayout) findViewById(R.id.host);
        addAppWidges();
    }
    
    void addAppWidges(){
        AppWidgetManager mAppWidgetManager=AppWidgetManager.getInstance(this);
        List<AppWidgetProviderInfo> widgets = mAppWidgetManager.getInstalledProviders();
        int i=0;
        for(AppWidgetProviderInfo appWidget:widgets){
            try{
                mWidgetHost=new AppWidgetHost(this, i++);
                final int appWidgetId = mWidgetHost.allocateAppWidgetId();
                if(mAppWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId, appWidget.provider)){
                    if(appWidget.configure!=null){
                        //TODO:启动配置activity
                    }else{
                        AppWidgetHostView widgetHostView=mWidgetHost.createView(this, appWidgetId, appWidget);
                        widgetHostView.setAppWidget(appWidgetId, appWidget);
//                        widgetHostView.setVisibility(View.VISIBLE);
                        mHost.addView(widgetHostView);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
