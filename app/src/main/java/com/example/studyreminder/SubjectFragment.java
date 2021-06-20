package com.example.studyreminder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    Spinner sp;
    EditText addSubjectEntry;
    Button addSubBtn, deleteBtn;
    myDatabase myDB;
    List<String> arrayList;
    String subject, subjectString;
    ArrayList<String> subjects = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int positionOfSelectedDataFromSpinner;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        sp = view.findViewById(R.id.addSubjectSpinner);
        addSubBtn = view.findViewById(R.id.addSubBtn);
        addSubjectEntry = view.findViewById(R.id.addSubjectEntry);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        myDB = new myDatabase(getContext());
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
        deleteBtn.setOnClickListener(view12 -> delete());
        return view;
    }

    private void loadSpinnerData() {
        myDatabase db = new myDatabase(getActivity().getApplicationContext());
        List<String> add_subject = db.getSubject();
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, add_subject);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
    }

    private void add() {
        subject = addSubjectEntry.getText().toString();
        if (subject.trim().length() > 0) {
            myDatabase db = new myDatabase(getContext());
            db.addSubject(subject);
            loadSpinnerData();
            if (!subject.isEmpty()) {
                adapter.add(subject);
                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
                addSubjectEntry.setText("");
                arrayList.clear();
                arrayList.addAll(myDB.getSubject());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "Nothing to add!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        int pos = sp.getSelectedItemPosition();
        if (pos > -1) {
            adapter.remove(subjects.get(pos));
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Nothing to delete!", Toast.LENGTH_SHORT).show();
        }
    }
}