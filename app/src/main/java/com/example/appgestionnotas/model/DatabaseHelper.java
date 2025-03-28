package com.example.appgestionnotas.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "Universidad.db";
    private static  final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table students (student_id integer  primary key AUTOINCREMENT, name text, id_code text)");

    db.execSQL("create table notas (id integer primary key AUTOINCREMENT, student_id integer, value real, foreign key(student_id) references students(id)) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists students");
    db.execSQL("drop table if exists notes");
    onCreate(db);

    }
}
