
package com.weibo.wjzabc.mydemos.Exceptions;

import com.weibo.wjzabc.mydemos.CommonActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * java.lang.IllegalStateException attempt to re-open an already-closed object: SQLiteDatabase: /data/data/com.bbk.appstore/databases/appstore.db
--------------------
android.os.Parcel.readException
android.database.DatabaseUtils.readExceptionFromParcel
android.database.DatabaseUtils.readExceptionFromParcel
android.content.ContentProviderProxy.query
android.content.ContentResolver.query
android.content.ContentResolver.query
com.bbk.launcher2.ko.a
com.bbk.launcher2.ko.e
com.bbk.launcher2.bl.run
android.os.Handler.handleCallback
android.os.Handler.dispatchMessage
android.os.Looper.loop
android.os.HandlerThread.run

---软件商店协助Launcher分析处理
 */
public class ReOpenAlreadyClosedSQLiteDatabase extends CommonActivity {

    MySQLiteOpenHelper helper=new MySQLiteOpenHelper(this);
    @Override
    protected void setupButton(Button b, int index) {

        switch (index) {
            case 1:
                b.setText("query");
                b.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread th1=new Thread(){
                            public void run() {
                                Cursor c=getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null,
                                        null);
                                if(c!=null){
                                    c.close();
                                }
                            };
                        };
                        th1.start();
 
                    }
                });
                break;
            case 2:
                b.setText("close db");
                b.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MySQLiteOpenHelper helper=MySQLiteOpenHelper.getInstance(ReOpenAlreadyClosedSQLiteDatabase.this);
                        helper.getWritableDatabase().close();
                    }
                });
                break;
            case 3:
                b.setText("insert");
                b.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread th1=new Thread(){
                            public void run() {
                                ContentValues values=new ContentValues();
//                              public static final String TABLE_NAME = "entry";
//                              public static final String COLUMN_NAME_ENTRY_ID = "entryid";
//                              public static final String COLUMN_NAME_TITLE = "title";
//                              public static final String COLUMN_NAME_SUBTITLE = "subtitle";
                              
//                              values.put("_id", 0);
                              values.put("entryid", "ddd");
                              values.put("title", "aaa");
                              values.put("subtitle", "bbb");
                              getContentResolver().insert(MyContentProvider.CONTENT_URI,values);
                            };
                        };
                        th1.start();

                    }
                });
                break;
            default:
        }
    }
}
