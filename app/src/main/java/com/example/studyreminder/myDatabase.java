package com.example.studyreminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_name = "BookLibreary.db";
    private static final int DB_version = 1;

    private static final String Table_Name = "my_library";
    private static final String taskID = "_id";
    private static final String title = "task title";
    private static final String description= "description";
    private static final String date= "due_date";



//    @Override
//    public String getDatabaseName() {
//
//    }

    public myDatabase(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + Table_Name +
                " (" + taskID + " INTEGER PRIMARY KEY AUTOIN CREMENT, " +
                title + "TEXT " +
                description + "TEXT " +
                date + "TEXT );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }
}
