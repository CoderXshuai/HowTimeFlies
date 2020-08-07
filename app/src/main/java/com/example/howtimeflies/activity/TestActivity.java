package com.example.howtimeflies.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.howtimeflies.R;
import com.example.howtimeflies.base.BaseActivity;
import com.example.howtimeflies.util.Constant;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity {

    @BindView(R.id.pie_chart)
    PieChart pieChart;
    @BindView(R.id.bar_chart)
    BarChart barChart;
    @BindView(R.id.number_progress_bar)
    NumberProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_time_page_layout);
        ButterKnife.bind(this);
        //柱状图
        List<Float> data = new ArrayList<>();
        data.add(30f);
        data.add(30f);
        data.add(30f);
        data.add(20f);
        setPieChart(data, pieChart);
        //饼图
        List<Float> floatList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            floatList.add((float) (Math.random() * 30));
        }
        setBarChart(floatList, barChart);
        progressBar.setProgress(33);
    }


    private void setBarChart(List<Float> YDataSet, BarChart barChart) {
        barChart.setExtraTopOffset(30);
        barChart.setExtraBottomOffset(10);
        BarData barData;
        List<BarEntry> barEntryList = new ArrayList<>();
        ArrayList<Integer> dataColor = Constant.BAR_COLORS;
        for (int i = 0; i < YDataSet.size(); i++) {
            barEntryList.add(new BarEntry(i, YDataSet.get(i)));
        }
        BarDataSet barDataSet = new BarDataSet(barEntryList, "条形图");
        barDataSet.setColors(dataColor);
        barData = new BarData(barDataSet);
        //取消图例
        barChart.getLegend().setEnabled(false);
        //设置条形图简介
        Description description = new Description();
        //设置条形图文字
        description.setText("近七天使用情况");
        //自定义位置
        description.setPosition(450, 65);
        //简介文字颜色
        description.setTextColor(Color.WHITE);
        //简介文字大小
        description.setTextSize(15f);
        //简介文字对齐方式,居中
        description.setTextAlign(Paint.Align.CENTER);
        //文字加粗
        description.setTypeface(Typeface.DEFAULT_BOLD);
        //在柱状图上设置简介
        barChart.setDescription(description);
        // 设置是否可以缩放
        barChart.setScaleEnabled(false);

        // 设置柱子的宽度
        barData.setBarWidth(0.5f);
        barData.setValueTextSize(10f);
        // 获取 x 轴
        XAxis xAxis = barChart.getXAxis();
        xAxis.setTextColor(Color.parseColor("#007CFF"));
        //设置X轴数值
        xAxis.setValueFormatter(new IndexAxisValueFormatter(Constant.X_DATA_SET));
        // 设置 x 轴显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 取消垂直网格线
        xAxis.setDrawGridLines(false);
        // 设置 x 轴 坐标字体大小
        xAxis.setTextSize(10f);
        // 设置 x 坐标轴 颜色
        xAxis.setAxisLineColor(Color.parseColor("#007CFF"));
        // 设置 x 坐标轴 宽度
        xAxis.setAxisLineWidth(4f);

        //获取 右边 y 轴
        YAxis mRAxis = barChart.getAxisRight();
        // 隐藏 右边 Y 轴
        mRAxis.setEnabled(false);
        mRAxis.setDrawAxisLine(true);
        mRAxis.setDrawGridLines(false);
        // 获取 左边 Y轴
        YAxis mLAxis = barChart.getAxisLeft();
        // 取消 左边 Y轴 坐标线
        mLAxis.setDrawAxisLine(true);
        mLAxis.setEnabled(true);
        mLAxis.setAxisLineWidth(4f);
        mLAxis.setAxisLineColor(Color.parseColor("#007CFF"));
        // 设置 横向 网格线
        mLAxis.setDrawGridLines(true);
        // 设置 Y轴 的刻度数量
        mLAxis.setLabelCount(5);
        barChart.setData(barData);
        //刷新
        barChart.invalidate();
    }

    private void setPieChart(List<Float> values, PieChart pieChart) {
        //设置每个数据元素占比
        List<PieEntry> chartData = new ArrayList<>();
        //类别
        ArrayList<String> pieType = Constant.PIE_TYPES;
        //颜色
        ArrayList<Integer> pieColors = Constant.PIE_COLORS;
        for (int i = 0; i < values.size(); i++) {
            //value表示占比，label表示名字
            chartData.add(new PieEntry(values.get(i), pieType.get(i)));
        }
        //label表示图表名
        PieDataSet dataSet = new PieDataSet(chartData, "");
        //设置饼图颜色
        dataSet.setColors(pieColors);
        PieData pieData = new PieData(dataSet);
        //设置占比数字大小
        pieData.setValueTextSize(13f);
        //百分制显示
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        //数值颜色
        pieData.setValueTextColor(Color.BLACK);
        //数值加粗
//        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        //设置为非空心圆
        pieData.setDrawValues(true);
        //设置数值
        pieChart.setData(pieData);
        //启用百分制显示
        pieChart.setUsePercentValues(true);
        //去除饼图内标签
        pieChart.setDrawEntryLabels(false);
        //设置位置
        pieChart.setExtraOffsets(0, 15, 0, 0);
        //设置全圆
        pieChart.setDrawHoleEnabled(false);
        pieChart.getDescription().setEnabled(true);
        //设置图标图例
        Legend legend = pieChart.getLegend();
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setXOffset(20f);
        legend.setYOffset(-10f);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setTextSize(16f);
        legend.setEnabled(true);
        //设置简介
        Description description = pieChart.getDescription();
        //设置位置
        description.setPosition(450, 70);
        //设置文字
        description.setText("今日使用情况");
        //文字大小
        description.setTextSize(15f);
        //简介文字颜色
        description.setTextColor(Color.WHITE);
        //简介文字对齐方式,居中
        description.setTextAlign(Paint.Align.CENTER);
        //文字加粗
        description.setTypeface(Typeface.DEFAULT_BOLD);
        //刷新
        pieChart.invalidate();
    }
}
