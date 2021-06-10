package com.example.studyreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity {

    EditText descEntry, dateEntry;
    Spinner subjectEntry;
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
            String spinnerText = subjectEntry.getSelectedItem().toString();
            myDB.addTask(spinnerText.trim(),
                    descEntry.getText().toString().trim(),
                    Integer.valueOf(dateEntry.getText().toString().trim()));


        });
    }
}