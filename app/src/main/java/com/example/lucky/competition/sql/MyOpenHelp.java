package com.example.lucky.competition.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucky on 2019/1/27.
 */

public class MyOpenHelp extends SQLiteOpenHelper {
    /**
     * @param context 上下文
     * @param name    名称
     * @param factory 版本控制器一般null
     * @param version 数据库版本号
     */
    public MyOpenHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "create table if not exists account(" +
                "_id integer primary key autoincrement," +
                "carNum text not null," +
                "money double not null," +
                "user text not null," +
                "timeDate timestamp" +
                ")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists account");
        String createTable = "create table if not exists account(" +
                "_id integer primary key autoincrement," +
                "carNum text not null," +
                "money double not null," +
                "user text not null," +
                "timeDate timestamp" +
                ")";
        sqLiteDatabase.execSQL(createTable);
    }
}
