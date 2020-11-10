package com.w32.exercicebd2.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseFactory extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CegepExercice2";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseFactory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CoursTable.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CoursTable.DROP_TABLE_SQL);
        onCreate(db);
    }
}
