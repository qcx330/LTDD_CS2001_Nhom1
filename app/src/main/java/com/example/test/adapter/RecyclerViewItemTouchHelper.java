package com.example.test.adapter;

import android.graphics.Canvas;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.view.View;

public class RecyclerViewItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ItemTouchHelperListener listener;
    public RecyclerViewItemTouchHelper(int dragDirs, int swipeDirs, ItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener!= null)
            listener.onSwiped(viewHolder);
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            LinearLayout foreground = ((RcVwAdapter.TaskViewHolder) viewHolder).foreground;
            getDefaultUIUtil().onSelected(foreground);
        }
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        LinearLayout foreground = ((RcVwAdapter.TaskViewHolder)viewHolder).foreground;
        getDefaultUIUtil().onDrawOver(c,recyclerView ,foreground, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        LinearLayout foreground = ((RcVwAdapter.TaskViewHolder)viewHolder).foreground;
        getDefaultUIUtil().onDraw(c,recyclerView ,foreground, dX, dY, actionState, isCurrentlyActive);

    }
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        LinearLayout foreground = ((RcVwAdapter.TaskViewHolder)viewHolder).foreground;
        getDefaultUIUtil().clearView(foreground);
    }
}
