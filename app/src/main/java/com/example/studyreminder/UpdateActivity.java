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


        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(subject);
        }

        addBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                String description = descEntry2.getText().toString().trim();
                String subject = subjectEntry2.getText().toString().trim();
                String dueDate = dateEntry2.getText().toString().trim();

                //to check if the user enters right values, if they do they can continue
                //else they will get a toast message
                if (description.length() > 0 && description.length() < 25) {
                    if (subject.length() > 0 && subject.length() < 15) {
                        if (dueDate.matches("^\\d{2}/\\d{2}")) {
                            myDB.updateData(id, subject, description, dueDate);
                            finish();
                        } else {
                            Toast.makeText(UpdateActivity.this, "Please use dd/mm format", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UpdateActivity.this, "Must be more than 1 and less than 15 letters", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdateActivity.this, "Subject must be more than 1 and less than 15 letters", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    //getting the data from the database and then putting it into the EditTexts
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
            Log.d("set ", subject+" "+description+" "+dueDate);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //delete the task
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
