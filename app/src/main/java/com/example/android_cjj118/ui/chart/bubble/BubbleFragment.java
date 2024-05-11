package com.example.android_cjj118.ui.chart.bubble;

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
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class BubbleFragment extends BaseFragment2 {

    private BubbleChart mBubbleChart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bubble,container,false);
        mBubbleChart = root.findViewById(R.id.mBubbleChart);
        mBubbleChart.getDescription().setEnabled(false);
        mBubbleChart.setDrawGridBackground(false);
        mBubbleChart.setTouchEnabled(true);
        mBubbleChart.setDragEnabled(true);
        mBubbleChart.setScaleEnabled(true);
        mBubbleChart.setMaxVisibleValueCount(200);
        mBubbleChart.setPinchZoom(true);

        YAxis yl = mBubbleChart.getAxisLeft();
        yl.setSpaceTop(30f);
        yl.setSpaceBottom(30f);
        yl.setDrawZeroLine(false);

        mBubbleChart.getAxisRight().setEnabled(false);

        XAxis xl = mBubbleChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        //自定义值的格式
        xl.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int)value+"天";
            }
        });

        Legend l = mBubbleChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        //设置描述
        Description description = mBubbleChart.getDescription();
        description.setText("学C人数");
        description.setTextColor(Color.BLACK);
        description.setTextSize(16f);
        description.setTextAlign(Paint.Align.CENTER);
//        description.setPosition(540f,100f);
        LimitLine limitLine = new LimitLine(10000f,"学C学员数");//参考线
        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(2f);
        yl.addLimitLine(limitLine);//对y轴添加参考线

        setData();
        return root;
    }
    //设置数据
    private void setData() {

        ArrayList<BubbleEntry> yVals1 = new ArrayList<BubbleEntry>();
        ArrayList<BubbleEntry> yVals2 = new ArrayList<BubbleEntry>();
        ArrayList<BubbleEntry> yVals3 = new ArrayList<BubbleEntry>();

        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * 300);
            float size = (float) (Math.random() * 400);

            yVals1.add(new BubbleEntry(i, val, size));
        }

        for (int i = 0; i < 20; i++) {
            float val = (float) (Math.random() * 400);
            float size = (float) (Math.random() * 500);

            yVals2.add(new BubbleEntry(i, val, size));
        }

        for (int i = 0; i < 30; i++) {
            float val = (float) (Math.random() * 500);
            float size = (float) (Math.random() * 600);

            yVals3.add(new BubbleEntry(i, val, size));
        }

        BubbleDataSet set1 = new BubbleDataSet(yVals1, "C");
        //可以谁知alpha
        set1.setColor(ColorTemplate.COLORFUL_COLORS[0]);
        set1.setDrawValues(true);
        BubbleDataSet set2 = new BubbleDataSet(yVals2, "C++");
        set2.setColor(ColorTemplate.COLORFUL_COLORS[1]);
        set2.setDrawValues(true);
        BubbleDataSet set3 = new BubbleDataSet(yVals3, "C#");
        set3.setColor(ColorTemplate.COLORFUL_COLORS[2]);
        set3.setDrawValues(true);

        ArrayList<IBubbleDataSet> dataSets = new ArrayList<IBubbleDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BubbleData data = new BubbleData(dataSets);
        data.setDrawValues(false);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.WHITE);
        data.setHighlightCircleWidth(1.5f);

        mBubbleChart.setData(data);
        mBubbleChart.invalidate();

        //默认动画
        mBubbleChart.animateXY(3000, 3000);
    }

}