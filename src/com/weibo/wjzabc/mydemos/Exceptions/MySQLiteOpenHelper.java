package com.weibo.wjzabc.mydemos.Exceptions;

import com.weibo.wjzabc.mydemos.Exceptions.MyProviderContract.Table_A;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + Table_A.TABLE_NAME + " (" +
        Table_A._ID + " INTEGER PRIMARY KEY," +
        Table_A.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
        Table_A.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
        Table_A.COLUMN_NAME_SUBTITLE + TEXT_TYPE +
        //... // Any other options for the CREATE command
        " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + Table_A.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static MySQLiteOpenHelper mInstance;

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public static MySQLiteOpenHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new MySQLiteOpenHelper(context);
        }
        return mInstance;
    }
    
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
}
