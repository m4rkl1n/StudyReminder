package com.example.studyreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class myDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_name = "allTasks.db";
    private static final int DB_version = 4;
    private static final String TABLE_NAME = "my_tasks";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "task_title";
    private static final String COLUMN_DESCRIPTION = "task_description";
    private static final String COLUMN_DUE_DATE = "due_date";

    private static final String TABLESUBJECTS = "subjects";
    private static final String ADDSUBJECT = "add_subject";


//    @Override
//    public String getDatabaseName() {
//
//    }

    public myDatabase(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DUE_DATE + " INTEGER);";
        db.execSQL(query);
        String addSubject = "CREATE TABLE " + TABLESUBJECTS +
                "(" + ADDSUBJECT + " TEXT);";
        db.execSQL(addSubject);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLESUBJECTS);
        onCreate(db);

    }


    public boolean addSubject(String add_subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ADDSUBJECT, add_subject);
        long result = db.insert(TABLESUBJECTS, null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public List<String> getSubject() {
        List<String> subjects = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<String> arrayList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLESUBJECTS, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            subjects.add(c.getString(c.getColumnIndex("add_subject")));
            c.moveToNext();
        }
        return subjects;
    }


    void addTask(String subject, String description, Integer dueDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, subject);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_DUE_DATE, dueDate);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void deleteOneRow(String row_subject){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLESUBJECTS, "subjects", new String[]{row_subject});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}

