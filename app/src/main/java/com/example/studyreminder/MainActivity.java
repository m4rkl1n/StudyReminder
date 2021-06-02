package com.example.studyreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton newTaskBtn;

    myDatabase myDB;
    ArrayList<String> task_id, task_subject, task_description, task_due_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerTasks);
        newTaskBtn = findViewById(R.id.floatingBtn);
        newTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        myDB = new myDatabase(MainActivity.this);
        task_subject = new ArrayList<>();
        task_description = new ArrayList<>();
        task_due_date = new ArrayList<>();

        storeDataArray();

        customAdapter = new CustomAdapter(MainActivity.this, task_subject, task_description, task_due_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            }
    void storeDataArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0 ){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()) {
                task_subject.add(cursor.getString(1));
                task_description.add(cursor.getString(2));
                task_due_date.add(cursor.getString(3));
            }
        }
    }


}