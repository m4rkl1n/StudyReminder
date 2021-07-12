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

public class MyDatabase extends SQLiteOpenHelper {

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



    public MyDatabase(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
        this.context = context;
    }

//used to create each set of data
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DUE_DATE + " BLOB);";
        db.execSQL(query);
        String addSubject = "CREATE TABLE " + TABLESUBJECTS +
                "(" + ADDSUBJECT + " TEXT);";
        db.execSQL(addSubject);
    }

    //used for upgrading versions of database
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLESUBJECTS);
        onCreate(db);

    }

//adding a subject to database
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

    //getting the subject
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


// code for getting one row of data
    void addTask(String subject, String description, String dueDate) {
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

    //used fo rwhen the data values are updated.
    public void updateData(String row_id, String title, String description, String dueDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_DUE_DATE, dueDate);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    //allows other classes to read this data.
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //deleting one task
    public void deleteOneRow(String row_task){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_task});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    void deleteAllSubjects(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLESUBJECTS);
    }


}

