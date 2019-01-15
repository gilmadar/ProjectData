package com.example.yam.projectdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbexam.db";
    private static int DATABASE_VERSION = 1000;
    String strCreate,strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        strCreate="CREATE TABLE "+Personal.TABLE_PERSONAL;
        strCreate+=" ("+Personal.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+Personal.NAME+" TEXT,";
        strCreate+=" "+Personal.IDNUMBER+" TEXT";
        strCreate+=");";
        sqLiteDatabase.execSQL(strCreate);


        strCreate="CREATE TABLE "+Student.TABLE_STUDENT;
        strCreate+=" ("+Student.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+Student.AGE+" INT,";
        strCreate+=" "+Student.CLASS+" TEXT,";
        strCreate+=" "+Student.TEACHERNAME+" TEXT";
        strCreate+=");";
        sqLiteDatabase.execSQL(strCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        strDelete = "DROP TABLE IF EXISTS "+Personal.TABLE_PERSONAL;
        sqLiteDatabase.execSQL(strDelete);
        strDelete = "DROP TABLE IF EXISTS "+Student.TABLE_STUDENT;
        sqLiteDatabase.execSQL(strDelete);

        onCreate(sqLiteDatabase);

    }
}