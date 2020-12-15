package com.example.howtimeflies.activity.ui.time;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.howtimeflies.R;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private final List<Time> timeList;

    public TimeAdapter(List<Time> timeList) {
        this.timeList = timeList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_time_item, parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Time time = timeList.get(position);
        holder.getImageView(R.id.ic_type);
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }
}
