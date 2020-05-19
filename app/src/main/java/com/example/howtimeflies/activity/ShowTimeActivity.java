package com.example.howtimeflies.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.howtimeflies.R;
import com.example.howtimeflies.base.BaseActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.xuexiang.xui.utils.ColorUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowTimeActivity extends BaseActivity {
    @BindView(R.id.pie_chart)
    PieChart pieChart;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_time_page_layout);
        ButterKnife.bind(this);
        setPieChart();
    }

    public void setPieChart() {
        //设置每个数据元素占比
        List<PieEntry> chartData = new ArrayList<>();
        //value表示占比，label表示名字
        chartData.add(new PieEntry(30f, "aaa"));
        chartData.add(new PieEntry(60f, "bbb"));
        chartData.add(new PieEntry(10f, "ccc"));
        //label表示图表名
        PieDataSet dataSet = new PieDataSet(chartData, "");
        //设置每个数据元素的颜色，数据元素添加顺序和颜色添加顺序一致
        ArrayList<Integer> dataColor = new ArrayList<>();
        dataColor.add(Color.parseColor("#4A92FC"));
        dataColor.add(Color.parseColor("#ee6e55"));
        dataColor.add(ColorUtils.getRandomColor());
        dataSet.setColors(dataColor);
        PieData pieData = new PieData(dataSet);
        //设置为非空心圆
        pieData.setDrawValues(true);
        pieChart.setData(pieData);
        pieChart.setDrawHoleEnabled(false);
        pieChart.getDescription().setEnabled(true);
        //设置图标图例
        Legend legend = pieChart.getLegend();
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTextSize(16f);
        legend.setEnabled(true);
        //刷新
        pieChart.invalidate();
    }

}
