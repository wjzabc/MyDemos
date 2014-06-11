package com.weibo.wjzabc.mydemos.Exceptions;

import android.provider.BaseColumns;

public final class MyProviderContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public MyProviderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Table_A implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        //...
    }
    
    /* Inner class that defines the table contents */
    public static abstract class Table_B implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        //...
    }
}
