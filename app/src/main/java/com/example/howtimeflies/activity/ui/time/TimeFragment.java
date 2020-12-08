package com.example.howtimeflies.activity.ui.time;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.howtimeflies.R;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pie_chart)
    PieChart pieChart;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.bar_chart)
    BarChart barChart;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeFragment newInstance(String param1, String param2) {
        TimeFragment fragment = new TimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        List<Float> data = new ArrayList<>();
        data.add(30f);
        data.add(30f);
        data.add(30f);
        data.add(20f);
        setPieChart(data, pieChart);
        List<Float> floatList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            floatList.add((float) (Math.random() * 30));
        }
        setBarChart(floatList, barChart);
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time, container, false);
    }
}