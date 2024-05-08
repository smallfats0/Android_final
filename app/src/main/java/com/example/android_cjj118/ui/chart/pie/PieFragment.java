package com.example.android_cjj118.ui.chart.pie;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;
import com.example.android_cjj118.ui.chart.line.LineViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class PieFragment extends BaseFragment2 {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pie, container, false);
        PieChart chart = root.findViewById(R.id.pieChart);
        PieViewModel pieViewModel = new ViewModelProvider(this).get(PieViewModel.class);
        pieViewModel.getPieList().observe(getViewLifecycleOwner(), PieBeans -> {
            List<PieEntry> entries = new ArrayList<>();
            for (int i = 0; i < PieBeans.size(); i++) {
                entries.add(new PieEntry(PieBeans.get(i).getPercentage(), PieBeans.get(i).getSalaries()));
            }
            PieDataSet dataSet = new PieDataSet(entries, "工资占比"); // 折线的数据
            dataSet.setColors(Color.GRAY,Color.BLUE,Color.GREEN,Color.RED); // 折线的颜色
            dataSet.setValueTextColor(Color.WHITE); // styling, ...
            dataSet.setValueTextSize(12f);
            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return value + "%";
                }
            });

            PieData pieData = new PieData(dataSet);
            chart.setData(pieData);
            chart.invalidate(); // refresh
            chart.setCenterText("点击现实\n相关数据");
            chart.setCenterTextColor(Color.BLACK);
            chart.setCenterTextSize(24f);
            chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    chart.setCenterText(((PieEntry)e).getLabel() + "\n" + ((PieEntry)e).getValue() + "%");
                }

                @Override
                public void onNothingSelected() {
                    chart.setCenterText("点击现实\n相关数据");
                }
            });
            chart.setExtraTopOffset(18f);
            chart.invalidate(); // refresh
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            Description description = chart.getDescription();
            description.setText("Android工资占比与经验的关系");
            description.setTextAlign(Paint.Align.CENTER);
            description.setTextSize(16f);
            description.setTextColor(Color.BLACK);
            description.setPosition(540f, 100f);
            chart.animateXY(5000, 5000);
        });
        return root;
    }

}