package com.example.studyreminder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    Button addBtn2, delete_button;
    EditText descEntry2, dateEntry2, subjectEntry2;
    String id, description, dueDate, subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        descEntry2 = findViewById(R.id.descEntry2);
        dateEntry2 = findViewById(R.id.dateEntry2);
        addBtn2= findViewById(R.id.addbtn2);
        subjectEntry2 = findViewById(R.id.subjectEntry2);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(subject);
        }

        addBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                description = descEntry2.getText().toString().trim();
                dueDate =dateEntry2.getText().toString().trim();
                subject = subjectEntry2.getText().toString().trim();
                myDB.updateData(id, subject, description, dueDate);

            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("subject")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("dueDate")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            subject = getIntent().getStringExtra("subject");
            description = getIntent().getStringExtra("description");
            dueDate = getIntent().getStringExtra("dueDate");

            //Setting Intent Data
            subjectEntry2.setText(subject);
            descEntry2.setText(description);
            dateEntry2.setText(dueDate);
            Log.d("stev", subject+" "+description+" "+dueDate);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Task?");
        builder.setMessage("Are you sure you want to delete this  task?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
