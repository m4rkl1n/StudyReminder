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
    String id, description, subject, dueDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        description_input = findViewById(R.id.descEntry2);
        subject_input = findViewById(R.id.subjectEntry2);
        dueDate_input = findViewById(R.id.dateEntry2);
        update_button = findViewById(R.id.addbtn2);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    void getAndSetIntentData() {
        if(getIntent().hasExtra("id")
                && getIntent().hasExtra("description")
                && getIntent().hasExtra("subject")
                && getIntent().hasExtra("due_date")){
            id = getIntent().getStringExtra("id");
            description = getIntent().getStringExtra("description");
            subject = getIntent().getStringExtra("subject");
            dueDate = getIntent().getStringExtra("dueDate");


            description_input.setText(description);
            subject_input.setText(subject);
            dueDate_input.setText(dueDate);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

//    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
//    private Button updateCourseBtn, deleteCourseBtn;
//    private DBHandler dbHandler;
//    String courseName, courseDesc, courseDuration, courseTracks;
//
//
//        // initializing all our variables.
//        courseNameEdt = findViewById(R.id.idEdtCourseName);
//        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
//        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
//        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
//        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
//        deleteCourseBtn = findViewById(R.id.idBtnDelete);
//
//        // on below line we are initialing our dbhandler class.
//        dbHandler = new DBHandler(UpdateCourseActivity.this);
//
//        // on below lines we are getting data which
//        // we passed in our adapter class.
//        courseName = getIntent().getStringExtra("name");
//        courseDesc = getIntent().getStringExtra("description");
//        courseDuration = getIntent().getStringExtra("duration");
//        courseTracks = getIntent().getStringExtra("tracks");
//
//        // setting data to edit text
//        // of our update activity.
//        courseNameEdt.setText(courseName);
//        courseDescriptionEdt.setText(courseDesc);
//        courseTracksEdt.setText(courseTracks);
//        courseDurationEdt.setText(courseDuration);
//
//        // adding on click listener to our update course button.
//        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // inside this method we are calling an update course
//                // method and passing all our edit text values.
//                dbHandler.updateCourse(courseName, courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(), courseTracksEdt.getText().toString(), courseDurationEdt.getText().toString());
//
//                // displaying a toast message that our course has been updated.
//                Toast.makeText(UpdateCourseActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();
//
//                // launching our main activity.
//                Intent i = new Intent(UpdateCourseActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
//
//        // adding on click listener for delete button to delete our course.
//        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // calling a method to delete our course.
//                dbHandler.deleteCourse(courseName);
//                Toast.makeText(UpdateCourseActivity.this, "Deleted the course", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(UpdateCourseActivity.this, MainActivity.class);
//                startActivity(i);
//            }
//        });
    }
