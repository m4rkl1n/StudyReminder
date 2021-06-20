package com.example.studyreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class AddActivity extends AppCompatActivity {

    EditText descEntry, dateEntry;
    Spinner sp;
    Button addBtn;
    myDatabase myDB;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        AddActivitySpinner();
        sp = findViewById(R.id.subjectEntry);
        ArrayAdapter<String> adapter;
        descEntry = findViewById(R.id.descEntry);
        dateEntry = findViewById(R.id.dateEntry);
        addBtn = findViewById(R.id.addbtn);
        addBtn.setOnClickListener(view -> {
            myDatabase myDB = new myDatabase(AddActivity.this);
            String spinnerText = sp.getSelectedItem().toString();
            myDB.addTask(spinnerText.trim(),
                    descEntry.getText().toString().trim(),
                    Integer.valueOf(dateEntry.getText().toString().trim()));
        });
    }

    private void AddActivitySpinner() {
        myDatabase myDB = new myDatabase(getApplicationContext());
        List<String> add_subject = myDB.getSubject();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, add_subject);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

    }
}