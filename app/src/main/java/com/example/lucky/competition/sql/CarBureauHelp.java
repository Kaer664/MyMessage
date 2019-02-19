package com.example.lucky.competition.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucky on 2019/2/18.
 */

public class CarBureauHelp extends SQLiteOpenHelper {

    public CarBureauHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table if not exists carBureauAccount(_id integer primary key autoincrement," +
                "licensePlate,money,alertBalance,name,time)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropSql="drop table if exists carBureauAccount";
        sqLiteDatabase.execSQL(dropSql);
        String sql="create table if not exists carBureauAccount(_id integer primary key autoincrement" +
                ",licensePlate,money,alertBalance,name,time)";
        sqLiteDatabase.execSQL(sql);
    }
}
