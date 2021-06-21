package com.example.studyreminder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myViewHolder> {

    private Context context;
    private ArrayList task_id, task_subject, task_description, task_due_date;

    CustomAdapter(Context context, ArrayList task_id, ArrayList task_subject, ArrayList task_description, ArrayList task_due_date){
        this.context = context;
        this.task_id = task_id;
        this.task_description = task_description;
        this.task_subject = task_subject;
        this.task_due_date = task_due_date;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from (context);
        View view = inflater.inflate(R.layout.to_do_list, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        holder.taskid_txt.setText(String.valueOf(task_id.get(position)));
        holder.taskSubject_txt.setText(String.valueOf(task_subject.get(position)));
        holder.taskDescription_txt.setText(String.valueOf(task_description.get(position)));
        holder.taskDate_txt.setText(String.valueOf(task_due_date.get(position)));
    }


    @Override
    public int getItemCount() {
        return task_id.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView taskid_txt, taskSubject_txt, taskDescription_txt, taskDate_txt;
        LinearLayout mainLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            taskid_txt = itemView.findViewById(R.id.taskid_txt);
            taskSubject_txt = itemView.findViewById(R.id.taskSubject_txt);
            taskDescription_txt = itemView.findViewById(R.id.taskDescription_txt);
            taskDate_txt = itemView.findViewById(R.id.taskDate_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }

    }

    ;
}
