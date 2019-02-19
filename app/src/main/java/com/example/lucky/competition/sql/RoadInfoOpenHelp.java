package com.example.lucky.competition.sql;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucky on 2019/2/17.
 */

public class RoadInfoOpenHelp extends SQLiteOpenHelper{
    public RoadInfoOpenHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table road(_id integer primary key autoincrement,temperature,humidity,light,co,pm,road)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="drop table if exists road";
        sqLiteDatabase.execSQL(sql);
        String sql2="create table road(_id integer primary key autoincrement,temperature,humidity,light,co,pm,road)";
        sqLiteDatabase.execSQL(sql2);
    }
}
