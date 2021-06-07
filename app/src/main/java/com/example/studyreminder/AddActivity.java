package com.example.studyreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText subjectEntry, descEntry, dateEntry;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        subjectEntry = findViewById(R.id.subjectEntry);
        descEntry = findViewById(R.id.descEntry);
        dateEntry =  findViewById(R.id.dateEntry);
        addBtn = findViewById(R.id.addbtn);
        addBtn.setOnClickListener(view -> {
            myDatabase myDB = new myDatabase(AddActivity.this);
            myDB.addTask(subjectEntry.getText().toString().trim(),
                    descEntry.getText().toString().trim(),
                    Integer.valueOf(dateEntry.getText().toString().trim()));


        });
    }
}