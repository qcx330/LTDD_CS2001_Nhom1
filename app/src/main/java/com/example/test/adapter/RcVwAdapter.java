package com.example.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.TaskModel;

import java.util.ArrayList;
import java.util.List;


public class RcVwAdapter extends RecyclerView.Adapter<RcVwAdapter.TaskViewHolder> {

    private List<TaskModel> taskList = new ArrayList<>();

    public RcVwAdapter(List<TaskModel> taskList){
        this.taskList = taskList;
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_layout, parent,false);
        return new TaskViewHolder(v);
    }
    public boolean toBoolean (int num){
        return num!=0;
    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        final TaskModel item = taskList.get(position);
        holder.txtActivity.setText(item.getTask());
        //holder.txtTime.setText(item);
        holder.chbxDone.setChecked(toBoolean(item.getDone()));
        holder.chbxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        holder.chbxImp.setChecked(toBoolean(item.getImpo()));
        holder.chbxImp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView txtActivity;
        CheckBox chbxDone, chbxImp;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtActivity = itemView.findViewById(R.id.txtActivity);
            chbxImp = itemView.findViewById(R.id.chbxImp);
            chbxDone = itemView.findViewById(R.id.chbxDone);
        }
    }
}
