package com.example.android_cjj118.ui.chart.horizontalBar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class HorizontalBarFragment extends BaseFragment2 {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_horizontal_bar,container,false);
        HorizontalBarChart chart = root.findViewById(R.id.mHorizontalBarChart);
        HorizontalBarViewModel horizontalBarViewModel = new ViewModelProvider(this).get(HorizontalBarViewModel.class);
        horizontalBarViewModel.getBarList().observe(getViewLifecycleOwner(),barBeans -> {
            //添加数据
            List<BarEntry> entries1 = new ArrayList<BarEntry>();
            List<BarEntry> entries2 = new ArrayList<BarEntry>();
            for (int i = 0; i < barBeans.size(); i++) {
                entries1.add(new BarEntry(i,barBeans.get(i).getLineBean1().getSalaries()));
                entries2.add(new BarEntry(i,barBeans.get(i).getLineBean2().getSalaries()));
            }
            BarDataSet dataSet1 = new BarDataSet(entries1,"前端开发");
            dataSet1.setValueTextColor(Color.RED);
            dataSet1.setValueTextSize(10f);
            dataSet1.setColor(Color.RED);

            BarDataSet dataSet2 = new BarDataSet(entries2,"移动开发");
            dataSet2.setValueTextColor(Color.GREEN);
            dataSet2.setValueTextSize(10f);
            dataSet2.setColor(Color.GREEN);

            BarData barData = new BarData(dataSet1,dataSet2);
            barData.setBarWidth(0.45f);
            //设置比例
            chart.setData(barData);
            chart.groupBars(-0.5f,0.04f,0.03f);
            chart.invalidate();//刷新

            //X轴坐标值的设置
            XAxis xAxis = chart.getXAxis();
            xAxis.setAxisLineColor(Color.BLACK);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setAxisLineWidth(3f);
            xAxis.setTextSize(10f);
            xAxis.enableGridDashedLine(10f,10f,0f);
            xAxis.setTextColor(Color.BLACK);
            //自定义值的格式
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return barBeans.get((int) value).getLineBean1().getYear();
                }
            });
            //Y轴坐标的值的设置
            chart.getAxisRight().setEnabled(false);
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setAxisLineColor(Color.BLACK);
            yAxis.setAxisLineWidth(3f);
            yAxis.setTextSize(10f);
            yAxis.enableGridDashedLine(10f,10f,0f);
            yAxis.setAxisMinimum(0f);
            yAxis.setSpaceTop(38.2f);
            LimitLine limitLine = new LimitLine(10000f,"厦门市平均工资");//参考线
            limitLine.setLineColor(Color.YELLOW);
            limitLine.setLineWidth(2f);
            yAxis.addLimitLine(limitLine);//对y轴添加参考线
            //设置图例
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            //设置描述
            Description description = chart.getDescription();
            description.setText("前端和移动开发工程师经验与工资的对应情况");
            description.setTextColor(Color.BLACK);
            description.setTextSize(16f);
            chart.animateY(5000);
        });
        return root;
    }

}