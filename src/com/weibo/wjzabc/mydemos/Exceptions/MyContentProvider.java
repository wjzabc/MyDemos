package com.weibo.wjzabc.mydemos.Exceptions;

import com.weibo.wjzabc.mydemos.Exceptions.MyProviderContract.Table_A;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends android.content.ContentProvider {
    
    private static final String AUTHORITY = "com.weibo.wjzabc.qqq";

    MySQLiteOpenHelper mSQLiteOpenHelper;
    
    static final Uri CONTENT_URI = Uri.parse("content://" +
            AUTHORITY + "/" +  Table_A.TABLE_NAME);

    @Override
    public boolean onCreate() {
        mSQLiteOpenHelper=MySQLiteOpenHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Cursor ret = null;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ret = db.query(Table_A.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
        if (ret != null) {
            ret.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return ret;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = mSQLiteOpenHelper.getWritableDatabase();
        Uri ret = null;
        long rowID;
        rowID = db.insert(Table_A.TABLE_NAME, null, values);
        // ret = Uri.parse(Constants.LOGO_URI + "/" + rowID);
        // getContext().getContentResolver().notifyChange(Constants.LOGO_URI,
        // null);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }

}
