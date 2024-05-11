package com.example.android_cjj118.ui.chart.line;

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
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class Line2Fragment extends BaseFragment2 {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_line2, container, false);
        LineChart chart = root.findViewById(R.id.lineChart2);
        Line2ViewModel line2ViewModel = new ViewModelProvider(this).get(Line2ViewModel.class);
        line2ViewModel.getLine2List().observe(getViewLifecycleOwner(), lineBeans -> {
            //当获得线性实体数据后所执行的操作
            //添加数据
            List<Entry> entities1 = new ArrayList<>();
            List<Entry> entities2 = new ArrayList<>();
            for (int i = 0; i < lineBeans.size(); i++) {
                entities1.add(new Entry(i, lineBeans.get(i).getLineBean1().getSalaries()));
                entities2.add(new Entry(i, lineBeans.get(i).getLineBean2().getSalaries()));
            }
            LineDataSet dataSet1 = new LineDataSet(entities1, "黑某公司工资");
            dataSet1.setValueTextColor(Color.parseColor("#99FFFF"));
            dataSet1.setValueTextSize(12f);
            dataSet1.setColor(Color.parseColor("#99FFFF"));
            dataSet1.setLineWidth(6f);
            //设置折线图填充
            dataSet1.setDrawFilled(true);
            //设置填充颜色
            dataSet1.setFillColor(Color.parseColor("#99FFFF"));
            dataSet1.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置线条样式
            dataSet1.setDrawCircleHole(true);
            dataSet1.setValueTextSize(9f);

            LineDataSet dataSet2 = new LineDataSet(entities2, "某X公司工资");
            dataSet2.setValueTextColor(Color.parseColor("#FFFF99"));
            dataSet2.setValueTextSize(12f);
            dataSet2.setColor(Color.parseColor("#FFFF99"));
            dataSet2.setLineWidth(6f);
            //设置折线图填充
            dataSet2.setDrawFilled(true);
            //设置填充颜色
            dataSet2.setFillColor(Color.parseColor("#FFFF99"));
            dataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            dataSet2.setDrawCircleHole(true);
            dataSet2.setValueTextSize(9f);

            LineData lineData = new LineData(dataSet1, dataSet2);
            chart.setData(lineData);
            chart.invalidate();//用于刷新
            //X轴坐标值的设置
            XAxis xAxis = chart.getXAxis();
            xAxis.setAxisLineColor(Color.BLACK);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setAxisLineWidth(3f);
            xAxis.setTextSize(10f);
            xAxis.enableGridDashedLine(10f, 10f, 0f);
            xAxis.setTextColor(Color.BLACK);
            //自定义值的格式
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return lineBeans.get((int) value).getLineBean1().getYear();
                }
            });
            //Y轴坐标的值的设置
            chart.getAxisRight().setEnabled(false);
            YAxis yAxis = chart.getAxisLeft();
            yAxis.setAxisLineColor(Color.BLACK);
            yAxis.setAxisLineWidth(3f);
            yAxis.setTextSize(10f);
            yAxis.enableGridDashedLine(10f, 10f, 0f);
            yAxis.setAxisMinimum(0f);
            yAxis.setSpaceTop(38.2f);
            LimitLine limitLine = new LimitLine(10000f, "厦门市平均工资");//参考线
            limitLine.setLineColor(Color.MAGENTA);
            limitLine.setLineWidth(2f);
            yAxis.addLimitLine(limitLine);//对y轴添加参考线
            Legend l = chart.getLegend();
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            //设置描述
            Description description = chart.getDescription();
            description.setText("UI设计师经验与工资的对应情况");
            description.setTextColor(Color.BLACK);
            description.setTextSize(16f);
            description.setTextAlign(Paint.Align.CENTER);
            description.setPosition(540f, 100f);
            chart.animateX(5000);
        });
        return root;
    }
}