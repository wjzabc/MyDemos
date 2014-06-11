package com.weibo.wjzabc.mydemos.database;

import com.weibo.wjzabc.mydemos.LogUtility;

import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

public class CursorContentObserver extends android.database.ContentObserver {

    private Cursor mCursor;

    public CursorContentObserver(Handler handler,Cursor cursor) {
        super(handler);
        mCursor = cursor;
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void onChange(boolean selfChange) {
        // TODO Auto-generated method stub
        super.onChange(selfChange);
        LogUtility.d(LogUtility.TAG.CursorContentObserver,"onchange 1");
    }
    

}
