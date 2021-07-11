package com.example.studyreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddActivity extends AppCompatActivity {

    EditText descEntry, dateEntry, remindIn;
    Spinner sp;
    Button addBtn;
    String sDate, sSubject, sDescription, sRemindIn;
    ArrayAdapter<String> adapter;
    int IntDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDatabase myDB = new MyDatabase(AddActivity.this);
        setContentView(R.layout.activity_add);
        createNotificationChannel();
        descEntry = findViewById(R.id.descEntry);
        InputFilter[] inputFilter = new InputFilter[1];
        inputFilter[0] = new InputFilter.LengthFilter(25);
        descEntry.setFilters(inputFilter);
        dateEntry = findViewById(R.id.dateEntry);
        addBtn = findViewById(R.id.addbtn);
        remindIn = findViewById(R.id.remindIn);
        sp = findViewById(R.id.subjectEntry);
        List<String> add_subject = myDB.getSubject();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, add_subject);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        addBtn.setOnClickListener(view -> {
            sDate = dateEntry.getText().toString().trim();
            sRemindIn = remindIn.getText().toString().trim();
            int intDate = Integer.parseInt(sRemindIn);
            sDescription = descEntry.getText().toString().trim();
            String spinnerText = sp.getSelectedItem().toString();
            sSubject = spinnerText.trim();
            Intent intent = new Intent(AddActivity.this, NotificationReminder.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this, 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();
//            long dateInDays = 86400000*intDate;
            long dateInDays = 1000*intDate;

            alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + dateInDays, pendingIntent);


            if (sDate.matches("^\\d{2}/\\d{2}")) {
                if(sDescription.length() >1 && sDescription.length() <26) {
                    if(intDate < 101 && intDate >0) {
                        myDB.addTask(sSubject,
                                sDescription,
                                sDate);
                        intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(this, "reminding day cannot be under 1 or over than 100", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Make sure you have more than 1 and less than 25 characters", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Please use dd/mm format", Toast.LENGTH_SHORT).show();
            }



        });
    }
    void createNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "ReminderChannel";
            String description = "Channel for Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("notifyStudy", name, importance);
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}