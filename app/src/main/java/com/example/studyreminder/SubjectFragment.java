package com.example.studyreminder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    Spinner sp;
    EditText addSubjectEntry;
    Button addSubBtn, deleteBtn, deleteAllBtn;
    MyDatabase myDB;
    List<String> arrayList;
    String subject, deleteSubject;
    ArrayList<String> subjects = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int positionOfSelectedDataFromSpinner;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        sp = view.findViewById(R.id.addSubjectSpinner);
        addSubBtn = view.findViewById(R.id.addSubBtn);
        addSubjectEntry = view.findViewById(R.id.addSubjectEntry);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        deleteAllBtn = view.findViewById(R.id.deleteAllBtn);
        myDB = new MyDatabase(getContext());
        arrayList = myDB.getSubject();
        loadSpinnerData();
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addSubBtn.setOnClickListener(view1 -> add());
        deleteAllBtn.setOnClickListener(view12 -> confirmDialog());
        return view;
    }

    private void loadSpinnerData() {
        MyDatabase db = new MyDatabase(getActivity().getApplicationContext());
        List<String> add_subject = db.getSubject();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, add_subject);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }

    private void add() {
        subject = addSubjectEntry.getText().toString();
        if (subject.trim().length() > 0 && subject.length() < 15) {
            MyDatabase db = new MyDatabase(getContext());
            db.addSubject(subject);
            loadSpinnerData();
            adapter.add(subject);
            Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
            addSubjectEntry.setText("");
            arrayList.clear();
            arrayList.addAll(myDB.getSubject());
            adapter.notifyDataSetChanged();
            }else{
            Toast.makeText(getContext(), "this subject is too short or too long", Toast.LENGTH_SHORT).show();
        }
    }



    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all subjects?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDB = new MyDatabase(getActivity());
                myDB.deleteAllSubjects();
                //Refresh Activity
                loadSpinnerData();
                Toast.makeText(getContext(), "Deleted all subjects", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    void confirmSubjectDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all subjects?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase myDB = new MyDatabase(getActivity());
//                myDB.deleteOneSubject();
                //Refresh Activity
                loadSpinnerData();
                Toast.makeText(getContext(), "Deleted all subjects", Toast.LENGTH_SHORT).show();
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