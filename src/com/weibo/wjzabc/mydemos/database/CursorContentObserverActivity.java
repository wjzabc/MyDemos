
package com.weibo.wjzabc.mydemos.database;

import com.weibo.wjzabc.mydemos.LogUtility;
import com.weibo.wjzabc.mydemos.R;
import com.weibo.wjzabc.mydemos.R.layout;
import com.weibo.wjzabc.mydemos.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;

public class CursorContentObserverActivity extends Activity {

    private boolean isRegister=false;
    private Cursor mDownloadCursor;
    private CursorContentObserver mDownloadingCursorObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_content_observer);
        startQuery();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cursor_content_observer, menu);
        return true;
    }
    
    @Override
    public void onBackPressed() {
        if(mDownloadCursor==null){
            LogUtility.d(LogUtility.TAG.CursorContentObserver,"mDownloadCursor=null");
        }else{
            LogUtility.d(LogUtility.TAG.CursorContentObserver,"mDownloadCursor.getCount()="+mDownloadCursor.getCount());
            LogUtility.d(LogUtility.TAG.CursorContentObserver,"count="+((DownloadManager) getSystemService(DOWNLOAD_SERVICE)).query(new Query() ).getCount());
//            mDownloadCursor.requery();
        }
        ((DownloadManager) getSystemService(DOWNLOAD_SERVICE)).enqueue(new Request(Uri.parse("http://download0.inner.bbk.com/appstore/upload/soft/201312/20131212155551656.apk")));
    }
    
    private void startQuery(){
        DownloadManager manager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        String uri=manager.
//        QueryHelper helper=new QueryHelper(this.getContentResolver());
//        helper.startQuery(0, null, Uri.parse("content://downloads/my_downloads"), null, null,null, null);
        manager.query(new Query());
        register(manager.query(new Query()));
    }
    
    class QueryHelper extends AsyncQueryHandler{


        public QueryHelper(ContentResolver cr) {
            super(cr);
            // TODO Auto-generated constructor stub
        }
        
        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            register(cursor);
        }
        
    }
    
    private void register(Cursor cursor){

        if (!isRegister) {
            mDownloadCursor = cursor;
            mDownloadingCursorObserver = new CursorContentObserver(new Handler(), mDownloadCursor);
            mDownloadCursor.registerContentObserver(mDownloadingCursorObserver);
            isRegister = true;
        }
    }
    
    private void unRegister(){
        
        if (isRegister) {
            if (mDownloadCursor != null && !mDownloadCursor.isClosed()) {
                mDownloadCursor.unregisterContentObserver(mDownloadingCursorObserver);
                mDownloadCursor.close();
            }
            isRegister = false;
        }
    }

}
