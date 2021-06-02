package com.example.studyreminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myViewHolder> {

    private Context context;
    private ArrayList task_subject, task_description, task_due_date;

    CustomAdapter(Context context, ArrayList task_subject, ArrayList task_description, ArrayList task_due_date){
        this.context = context;
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
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.subject_txt.setText(String.valueOf(task_subject.get(position)));
        holder.description_txt.setText(String.valueOf(task_description.get(position)));
        holder.due_date_txt.setText(String.valueOf(task_due_date.get(position)));
    }

    @Override
    public int getItemCount() {
        return task_description.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView subject_txt, description_txt, due_date_txt;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            subject_txt = itemView.findViewById(R.id.taskSubject);
            description_txt = itemView.findViewById(R.id.taskDescription);
            due_date_txt = itemView.findViewById(R.id.taskDate);

        }

    }

    ;
}
