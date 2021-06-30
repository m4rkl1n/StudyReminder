package com.example.studyreminder;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TaskFragment extends Fragment {

    RecyclerView recyclerView;
    MyDatabase myDB;
    ArrayList<String> task_id, task_subject, task_description, task_due_date;
    CustomAdapter customAdapter;
    FloatingActionButton floatingBtn;
    Button deleteAllSubBtn;
    ConstraintLayout noDataView;
    Activity activity;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        recyclerView = view.findViewById(R.id.recyclerTasks);
        myDB = new MyDatabase(getActivity());
        task_id = new ArrayList<>();
        task_subject = new ArrayList<>();
        task_description = new ArrayList<>();
        task_due_date = new ArrayList<>();
        noDataView = view.findViewById(R.id.noDataView);
        deleteAllSubBtn = view.findViewById(R.id.deleteAllSubBtn);
        deleteAllSubBtn.setOnClickListener(view1 -> {
            confirmDialog();
        });

        floatingBtn = view.findViewById(R.id.floatingBtn);
        floatingBtn.setOnClickListener(view12 -> {
            Intent intent = new Intent(getActivity(), AddActivity.class);
            startActivity(intent);
        });

        customAdapter = new CustomAdapter(getContext(), getActivity(), task_id, task_subject, task_description, task_due_date);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(customAdapter);
        storeDataArray();
        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
//        storeDataArray();
    }


    void storeDataArray() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            noDataView.setVisibility(View.VISIBLE);

        } else {
            while (cursor.moveToNext()) {
                task_id.add(cursor.getString(0));
                task_subject.add(cursor.getString(1));
                task_description.add(cursor.getString(2));
                task_due_date.add(cursor.getString(3));
                noDataView.setVisibility(View.GONE);
            }
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDB = new MyDatabase(getActivity());
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
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
