package com.example.test.adapter;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchHelperListener {
    default void onSwiped(RecyclerView.ViewHolder viewHolder){

    }
}
