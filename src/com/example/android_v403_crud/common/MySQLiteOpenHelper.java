package com.example.android_v403_crud.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  SQLiteOpenHelper Class
 *
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    private static final String DB = "android_v403_crud_sqlite.db";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE_SQL = "create table mytable ( _id integer primary key autoincrement, data integer not null );";
    private static final String DROP_TABLE_SQL = "drop table mytable;";

    //Constractor
    public MySQLiteOpenHelper(Context c){
        super(c, DB, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_SQL);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_SQL);
        onCreate(db);
    }

}
