package com.example.test.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.model.TaskModel;

import java.util.ArrayList;
import java.util.List;


public class RcVwAdapter extends RecyclerView.Adapter<RcVwAdapter.TaskViewHolder> {

    private List<TaskModel> taskList;

    public void setTaskList(TaskModel taskList){
        this.taskList.add(taskList);
    }

    public List<TaskModel> getTaskList(){
        return this.taskList;
    }

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
                if(holder.chbxDone.isChecked())
                {
                    holder.txtActivity.setPaintFlags(holder.txtActivity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.chbxDone.setBackgroundResource(R.drawable.chbox_done);
                    item.setDone(1);
                    item.setImpo(1);
                    Toast.makeText(buttonView.getContext(), String.valueOf(taskList.indexOf(item)), Toast.LENGTH_LONG).show();
                }
                else {
                    holder.txtActivity.setPaintFlags(holder.txtActivity.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    item.setDone(0);
                    item.setImpo(0);
                }
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
