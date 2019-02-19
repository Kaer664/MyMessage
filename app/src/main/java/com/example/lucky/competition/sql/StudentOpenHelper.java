package com.example.lucky.competition.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucky on 2019/2/5.
 */

public class StudentOpenHelper  extends SQLiteOpenHelper{
    public StudentOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table Student(_id integer primary key autoincrement,name,pwd)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="drop table if exists Student";
        sqLiteDatabase.execSQL(sql);  //删除表

        String sql2="create table Student(_id integer primary key autoincrement,name,pwd)";
        sqLiteDatabase.execSQL(sql2);   //修改表结构
    }
}
