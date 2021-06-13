package com.example.studyreminder;

import android.content.Intent;
import android.database.Cursor;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SubjectFragment extends Fragment {

    Spinner sp;
    RecyclerView recyclerView;
    FloatingActionButton newTaskBtn;
    EditText addSubjectEntry;
    Button addSubBtn, deleteBtn;
    myDatabase myDB;
    ArrayList<String> subjects = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        sp = view.findViewById(R.id.addSubjectSpinner);
        addSubBtn = view.findViewById(R.id.addSubBtn);
        addSubjectEntry = view.findViewById(R.id.addSubjectEntry);
        deleteBtn = view.findViewById(R.id.deleteBtn);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, subjects);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                addSubjectEntry.setText(subjects.get(pos));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addSubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });

//        return inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }

    private void add() {
        String subject = addSubjectEntry.getText().toString();
        if (!subject.isEmpty() && subject.length() > 0) {
            adapter.add(subject);
            adapter.notifyDataSetChanged();
            addSubjectEntry.setText("");
            Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Nothing to add!", Toast.LENGTH_SHORT).show();
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
