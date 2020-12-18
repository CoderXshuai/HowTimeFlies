package com.example.howtimeflies.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.howtimeflies.R;
import com.example.howtimeflies.entity.Time;
import com.example.howtimeflies.util.ToastUtils;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.MyViewHold> {
    private final List<Time> timeList;
    private final Context mContext;

    public TimeAdapter(List<Time> timeList, Context mContext) {
        this.timeList = timeList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_time_item, parent, false);
        return new MyViewHold(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHold holder, int position) {
        Time time = timeList.get(position);
        Glide.with(mContext).load(time.getIconID()).into(holder.iconID);
        holder.type.setText(time.getType());
        holder.progressNum.setText(String.valueOf(time.getProgressNum()));
        String strStudyHour = mContext.getResources().getString(R.string.study_hour);
        holder.studyHour.setText(String.format(strStudyHour, time.getStudyHour()));
        String strStudyMin = mContext.getResources().getString(R.string.study_min);
        holder.studyMin.setText(String.format(strStudyMin, time.getStudyMin()));
//        holder.detailUrl待完善
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public static class MyViewHold extends RecyclerView.ViewHolder {
        ImageView iconID;
        TextView type;
        TextView progressNum;
        TextView studyHour;
        TextView studyMin;
        TextView detailUrl;

        public MyViewHold(@NonNull View itemView) {
            super(itemView);
            iconID = itemView.findViewById(R.id.ic_type);
            type = itemView.findViewById(R.id.tv_type);
            progressNum = itemView.findViewById(R.id.process_num);
            studyHour = itemView.findViewById(R.id.tv_hour);
            studyMin = itemView.findViewById(R.id.tv_min);
            detailUrl = itemView.findViewById(R.id.tv_detail);
            detailUrl.setOnClickListener(v -> ToastUtils.info("detailUrl is clicked"));
        }
    }
}
