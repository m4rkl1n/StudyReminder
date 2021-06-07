package com.example.studyreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText description_input, subject_input, dueDate_input;
    Button update_button;
    String description, subject, due_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        description_input = findViewById(R.id.descEntry2);
        subject_input = findViewById(R.id.subjectEntry2);
        dueDate_input = findViewById(R.id.dateEntry2);
        update_button = findViewById(R.id.addbtn2);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getAndSetIntentData();
    }
    void getAndSetIntentData() {
        if(getIntent().hasExtra("description") && getIntent().hasExtra("subject") && getIntent().hasExtra("due_date")){
            description = getIntent().getStringExtra("description");
            subject = getIntent().getStringExtra("subject");
            due_date = getIntent().getStringExtra("due_date");

            description_input.setText(description);
            subject_input.setText(subject);
            dueDate_input.setText(due_date);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
}