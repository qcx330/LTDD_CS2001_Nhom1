package com.example.test.adapter;

import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
//import com.example.test.controller.TaskController;
import com.example.test.controller.TaskController;
import com.example.test.model.TaskModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class RcVwAdapter extends RecyclerView.Adapter<RcVwAdapter.TaskViewHolder> {
    SimpleDateFormat format = new SimpleDateFormat("h:mm aa");
    private List<TaskModel> taskList;
    TaskController taskController = new TaskController();

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
        holder.txtTime.setText(format.format(item.getTime()));
        holder.chbxDone.setChecked(toBoolean(item.getDone()));
        //Done
        holder.chbxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.chbxDone.isChecked())
                {
                    holder.txtActivity.setPaintFlags(holder.txtActivity.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    holder.chbxDone.setBackgroundResource(R.drawable.chbox_done);
                    item.setDone(1);
                    taskController.EditTask(item.getTask(), "done" , item.getDone());
                }
                else {
                    holder.txtActivity.setPaintFlags(holder.txtActivity.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    item.setDone(0);
                    taskController.EditTask(item.getTask(), "done" , item.getDone());
                }
            }
        });
        //Important
        holder.chbxImp.setChecked(toBoolean(item.getImpo()));
        holder.chbxImp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(holder.chbxImp.isChecked())
                {
                    item.setImpo(1);
                    taskController.EditTask(item.getTask(), "impo" , item.getImpo());
                }
                else {
                    item.setImpo(0);
                    taskController.EditTask(item.getTask(), "impo" , item.getImpo());
                }
            }
        });

        holder.setItemClickListener(new TaskViewHolder.ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
//                Toast.makeText(view.getContext(),taskList.get(position).getTask(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(view.getContext(),format.format(taskList.get(position).getTime()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtActivity, txtTime;
        CheckBox chbxDone, chbxImp;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        private ItemClickListener itemClickListener;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtActivity = itemView.findViewById(R.id.txtActivity);
            chbxImp = itemView.findViewById(R.id.chbxImp);
            chbxDone = itemView.findViewById(R.id.chbxDone);
            txtTime = itemView.findViewById(R.id.txtRemindTime);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }
        public interface ItemClickListener {
            void onClick(View view, int position,boolean isLongClick);
        }
    }
}
