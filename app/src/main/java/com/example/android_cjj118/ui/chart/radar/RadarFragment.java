package com.example.android_cjj118.ui.chart.radar;

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
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class RadarFragment extends BaseFragment2 {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_radar,container,false);
        RadarChart mRadarChart = root.findViewById(R.id.reader_chart);
        RadarViewModel radarViewModel = new ViewModelProvider(this).get(RadarViewModel.class);
        radarViewModel.getRadarList().observe(getViewLifecycleOwner(), RadarBeans -> {
            //添加数据
            List<RadarEntry> data1 = new ArrayList<>();
            List<RadarEntry> data2 = new ArrayList<>();
            List<RadarEntry> data3 = new ArrayList<>();
            List<RadarEntry> data4 = new ArrayList<>();
            List<RadarEntry> data5 = new ArrayList<>();
            List<RadarEntry> data6 = new ArrayList<>();
            for (int i = 0; i < RadarBeans.size(); i++) {
                data1.add(new RadarEntry(RadarBeans.get(i).getLineBean1().getSalaries()));
                data2.add(new RadarEntry(RadarBeans.get(i).getLineBean2().getSalaries()));
                data3.add(new RadarEntry(RadarBeans.get(i).getLineBean3().getSalaries()));
                data4.add(new RadarEntry(RadarBeans.get(i).getLineBean4().getSalaries()));
                data5.add(new RadarEntry(RadarBeans.get(i).getLineBean5().getSalaries()));
                data6.add(new RadarEntry(RadarBeans.get(i).getLineBean6().getSalaries()));
            }
            RadarDataSet dataSet1 = new RadarDataSet(data1,"思明区");
            dataSet1.setColor(Color.parseColor("#fbd06a"));
            dataSet1.setDrawFilled(true);
            dataSet1.setFillColor(Color.parseColor("#fbd06a"));
            dataSet1.setFillAlpha(180);
            dataSet1.setLineWidth(1f);

            RadarDataSet dataSet2 = new RadarDataSet(data2,"湖里区");
            dataSet2.setColor(Color.parseColor("#f69a40"));
            dataSet2.setDrawFilled(true);
            dataSet2.setFillColor(Color.parseColor("#f69a40"));
            dataSet2.setFillAlpha(180);
            dataSet2.setLineWidth(1f);

            RadarDataSet dataSet3 = new RadarDataSet(data3,"集美区");
            dataSet3.setColor(Color.parseColor("#ff5d52"));
            dataSet3.setDrawFilled(true);
            dataSet3.setFillColor(Color.parseColor("#ff5d52"));
            dataSet3.setFillAlpha(180);
            dataSet3.setLineWidth(1f);

            RadarDataSet dataSet4 = new RadarDataSet(data4,"翔安区");
            dataSet4.setColor(Color.parseColor("#e71f19"));
            dataSet4.setDrawFilled(true);
            dataSet4.setFillColor(Color.parseColor("#e71f19"));
            dataSet4.setFillAlpha(180);
            dataSet4.setLineWidth(1f);

            RadarDataSet dataSet5 = new RadarDataSet(data5,"海沧区");
            dataSet5.setColor(Color.parseColor("#ff9b43"));
            dataSet5.setDrawFilled(true);
            dataSet5.setFillColor(Color.parseColor("#ff9b43"));
            dataSet5.setFillAlpha(180);
            dataSet5.setLineWidth(1f);

            RadarDataSet dataSet6 = new RadarDataSet(data6,"同安区");
            dataSet6.setColor(Color.parseColor("#8eb9fb"));
            dataSet6.setDrawFilled(true);
            dataSet6.setFillColor(Color.parseColor("#8eb9fb"));
            dataSet6.setFillAlpha(180);
            dataSet6.setLineWidth(1f);

            RadarData radarData=new RadarData(dataSet1,dataSet2,dataSet3,dataSet4,dataSet5,dataSet6);
            mRadarChart.setData(radarData);
            mRadarChart.invalidate();
            mRadarChart.getYAxis().setAxisMinimum(0);
            // 绘制线条宽度，圆形向外辐射的线条
            mRadarChart.setWebLineWidth(1f);
            // 线条的颜色
            mRadarChart.setWebColor(Color.parseColor("#d5d5d5"));
            mRadarChart.setWebColorInner(Color.parseColor("#d5d5d5"));
            // 内部线条宽度，外面的环状线条
            mRadarChart.setWebLineWidthInner(0.4f);
            // 所有线条WebLine透明度
            mRadarChart.setWebAlpha(100);
            //取消图标右下角的描述
            mRadarChart.getDescription().setEnabled(false);
//        图显示的位置
            mRadarChart.setExtraOffsets(0, 2, 110, 0);
            XAxis xAxis = mRadarChart.getXAxis();
            //自定义值的格式
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    if((int) value!=7){//防止下标越界
                        return RadarBeans.get((int) value).getLineBean1().getYear();
                    }
                    return "null";
                }
            });
            // X坐标值字体大小
            xAxis.setTextSize(18f);
            xAxis.setGridColor(Color.parseColor("#a1a1a1"));
            xAxis.setTextColor(Color.parseColor("#a1a1a1"));
            xAxis.setAxisLineColor(Color.parseColor("#a1a1a1"));
            // Y坐标值字体样式

            YAxis yAxis = new YAxis();
            // Y坐标值标签个数
            yAxis.setLabelCount(6, false);
            // Y坐标值字体大小
            yAxis.setTextSize(15f);
            yAxis.setTextColor(Color.parseColor("#a1a1a1"));
            yAxis.setAxisLineColor(Color.parseColor("#a1a1a1"));
            // Y坐标值是否从0开始
            yAxis.setStartAtZero(true);
            yAxis.setEnabled(false);
            yAxis.setDrawLabels(false);
            yAxis.setDrawTopYLabelEntry(false);
            yAxis.setGridColor(Color.parseColor("#a1a1a1"));
            xAxis.setDrawLimitLinesBehindData(false);
            //设置X上的显示labal
            xAxis.setDrawLabels(true);
            //Y轴字体颜色
            yAxis.setTextColor(Color.parseColor("#a1a1a1"));


            Legend l = mRadarChart.getLegend();
            // 图例位置
//        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            // 图例X间距
            l.setXEntrySpace(1f);
            // 图例Y间距
            l.setYEntrySpace(1f);
//        图例文字的大小
            l.setTextSize(12f);
//        图例文字颜色
            l.setTextColor(Color.parseColor("#a1a1a1"));
//        图例相对于Y的偏移
            l.setYOffset(1f);
//        图例相对于X的偏移
            l.setXOffset(20f);

            //设置描述
            //对于右下角一串字母的操作
            mRadarChart.getDescription().setEnabled(true);                  //是否显示右下角描述
            mRadarChart.getDescription().setText("网络营销各地工资与经验对应情况");    //修改右下角字母的显示
            mRadarChart.getDescription().setTextSize(20);                    //字体大小
            mRadarChart.getDescription().setTextAlign(Paint.Align.CENTER);
            mRadarChart.getDescription().setTextColor(Color.BLACK);             //字体颜色
            mRadarChart.animateY(2000);
        });
        return root;
    }
}
