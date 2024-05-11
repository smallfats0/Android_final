package com.example.android_cjj118.ui.chart.pie;

import android.graphics.Color;
import android.graphics.Paint;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Pie2Fragment extends BaseFragment2 {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pie2,container,false);
        PieChart chart = root.findViewById(R.id.pieChart2);
        Pie2ViewModel pie2ViewModel = new ViewModelProvider(this).get(Pie2ViewModel.class);
        pie2ViewModel.getPieList().observe(getViewLifecycleOwner(),pieBeans -> {
            //添加数据
            List<PieEntry> entities = new ArrayList<PieEntry>();
            for (int i = 0; i < pieBeans.size(); i++) {
                entities.add(new PieEntry(pieBeans.get(i).getPercentage(),
                        pieBeans.get(i).getSalaries()));
            }
            PieDataSet dataSet = new PieDataSet(entities,"工资占比");
            dataSet.setValueTextColor(Color.WHITE);
            dataSet.setValueTextSize(12f);
            dataSet.setColors(Color.GRAY,Color.MAGENTA,Color.GREEN,Color.BLUE);
            PieData pieData = new PieData(dataSet);
            //自定义值的格式
            pieData.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value+"%";
                }
            });
            chart.setData(pieData);
            chart.setDrawHoleEnabled(false);
            chart.invalidate();//刷新
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            //设置描述
            Description description = chart.getDescription();
            description.setText("Python工程师经验与工资的对应情况");
            description.setTextColor(Color.BLACK);
            description.setTextSize(16f);
            description.setTextAlign(Paint.Align.CENTER);
            description.setPosition(540f,100f);
            chart.animateXY(5000,5000);//动画效果
        });
        return root;
    }
}