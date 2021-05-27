package com.example.studyreminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDatabase extends SQLiteOpenHelper {

    private Context context;
    public static final String DB_name = "BookLibreary.db";
    public static final int DB_version = 1;

    public static final String Table_Name = "my_library";
    public static final String taskID = "_id";
    public static final String title = "task title";
    public static final String description= "description";
    public static final String date= "due_date";



    @Override
    public String getDatabaseName() {

    }

    public myDatabase(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
