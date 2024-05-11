package com.example.android_cjj118.ui.chart.candle;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_cjj118.R;
import com.example.android_cjj118.base.BaseFragment2;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class candleFragment extends BaseFragment2 {
    private String[] years;
    private CandleStickChart candleStickChart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_candle,container,false);
        candleStickChart = root.findViewById(R.id.candleStickChart);
        setData();
        candleStickChart.getDescription().setText("");
        candleStickChart.getDescription().setTextColor(Color.RED);
        candleStickChart.getDescription().setTextSize(16);//设置描述的文字 ,颜色 大小
        candleStickChart.setNoDataText("无数据噢"); //没数据的时候显示
        candleStickChart.setDrawBorders(true);//是否显示边框
        candleStickChart.animateX(500);//x轴动画
        candleStickChart.setTouchEnabled(true); // 设置是否可以触摸
        candleStickChart.setDragEnabled(true);// 是否可以拖拽
        candleStickChart.setScaleEnabled(false);// 是否可以缩放 x和y轴, 默认是true
        candleStickChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        candleStickChart.setScaleYEnabled(true); //是否可以缩放 仅y轴
        candleStickChart.setPinchZoom(true);  //设置x轴和y轴能否同时缩放。默认是否
        candleStickChart.setDoubleTapToZoomEnabled(true);//设置是否可以通过双击屏幕放大图表。默认是true
        candleStickChart.setHighlightPerDragEnabled(true);//能否拖拽高亮线(数据点与坐标的提示线)，默认是true
        candleStickChart.setDragDecelerationEnabled(true);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        candleStickChart.setDragDecelerationFrictionCoef(0.99f);//与上面那个属性配合，持续滚动时的速度快慢，[0,1) 0代表立即停止

        //x轴的设置
        XAxis xAxis = candleStickChart.getXAxis();//获取x轴
        xAxis.setAxisMinimum(0);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴位置
        xAxis.setTextColor(Color.GRAY);//设置x轴字体颜色
        xAxis.setTextSize(14);//设置x轴文字字体大小
        xAxis.setDrawGridLines(false);//设置竖向线  网格线
        //自定义值的格式
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int)value+"分前";
            }
        });

        //y轴的设置
        YAxis yAxisLeft = candleStickChart.getAxisLeft();
        yAxisLeft.setAxisMaximum(200);
        yAxisLeft.setAxisMinimum(0);//设置左侧y轴
        yAxisLeft.setTextSize(14);//左侧y轴文字字体大小
        YAxis yAxisRight = candleStickChart.getAxisRight(); //设置右侧y轴
        yAxisRight.setEnabled(false);//设置右侧y轴是否可用
        LimitLine limitLine = new LimitLine(40f,"近期平均股票");//参考线
        limitLine.setLineColor(Color.MAGENTA);
        limitLine.setLineWidth(2f);
        yAxisLeft.addLimitLine(limitLine);//对y轴添加参考线
        /**
         * 设置图例
         */
        Legend legend = candleStickChart.getLegend();
        legend.setEnabled(true);//
        legend.setForm(Legend.LegendForm.SQUARE); //设置比例图样式 方
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//设置横向
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);//设置位置
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        String[] lables = new String[]{"涨","跌"};
        int[] colors = new int[]{Color.RED,Color.GREEN};
        LegendEntry incre = new LegendEntry("涨", Legend.LegendForm.SQUARE, 10f, 1f, null, colors[0]);
        LegendEntry decre = new LegendEntry("跌", Legend.LegendForm.SQUARE, 10f, 1f, null, colors[1]);
        legend.setCustom(new LegendEntry[]{incre,decre});

        //设置描述
        Description description = candleStickChart.getDescription();
        description.setText("股票情况");
        description.setTextColor(Color.BLACK);
        description.setTextAlign(Paint.Align.CENTER);
        description.setTextSize(16f);
        description.setPosition(540f,100f);
        return root;
    }
    //设置数据
    private void setData() {
        ArrayList<CandleEntry> yVals3 = new ArrayList<CandleEntry>();
        years= new String[]{"1-2年","2-3年",
                "3-5年","5-8年","8-10年"};
        /**
         * shadowH 当天的最高价
         * shadowL 当天的最低价
         * open 开盘价
         * close 收盘价
         */
        for (int i = 0; i < 30; i++) {
            float shadowH = (float) (Math.random() * 60);
            float shadowL = (float) (Math.random() * 90);
            float open = (float) (Math.random() * 40);
            float close = (float) (Math.random() * 70);
            yVals3.add(new CandleEntry(i, shadowH, shadowL,open,close));
        }
        CandleDataSet set3 = new CandleDataSet(yVals3, "C#");
        set3.setBarSpace(1f);
        set3.setDecreasingColor(Color.BLUE);
        set3.setShadowWidth(12f);
        set3.setShowCandleBar(true);
        set3.setAxisDependency(YAxis.AxisDependency.LEFT);
        set3.setShadowColorSameAsCandle(true);
        set3.setShadowWidth(0.7f);
        set3.setDecreasingColor(Color.GREEN);
        set3.setDecreasingPaintStyle(Paint.Style.FILL);
        set3.setIncreasingColor(Color.RED);
        set3.setIncreasingPaintStyle(Paint.Style.FILL);
        set3.setColors(ColorTemplate.LIBERTY_COLORS);
        CandleData data = new CandleData(set3);
        data.setDrawValues(false);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.WHITE);

        candleStickChart.setData(data);
        candleStickChart.invalidate();

        //默认动画
        candleStickChart.animateY(3000);
    }
}