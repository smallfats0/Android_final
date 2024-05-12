package com.example.android_cjj118.ui.me.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_cjj118.R;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.example.android_cjj118.base.BaseFragment2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FullCalendarFragment extends BaseFragment2 implements
        CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener,
        View.OnClickListener{

    TextView mTextMonthDay;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay;
    private int mYear;
    CalendarView mCalendarView;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_full_calendar, container, false);
        mTextMonthDay = root.findViewById(R.id.tv_month_day);
        mTextYear = root.findViewById(R.id.tv_year);
        mTextLunar = root.findViewById(R.id.tv_lunar);
        mTextCurrentDay = root.findViewById(R.id.tv_current_day);
        mCalendarView = root.findViewById(R.id.calendarView);
        initData();//添加数据
        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.showYearSelectLayout(mYear);
                mTextLunar.setVisibility(View.GONE);
                mTextYear.setVisibility(View.GONE);
                mTextMonthDay.setText(String.valueOf(mYear));
            }
        });
        root.findViewById(R.id.fl_current).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
            }
        });
        mCalendarView.setOnCalendarSelectListener(this);
        mCalendarView.setOnYearChangeListener(this);
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        return root;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCalendarOutOfRange(Calendar calendar) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCalendarSelect(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();
        if(isClick && !Objects.equals(calendar.getScheme(), "")){
            String year = String.valueOf(calendar.getYear());
            String month = String.valueOf(calendar.getMonth());
            String day = String.valueOf(calendar.getDay());
            String scheme = calendar.getScheme();
            Toast.makeText(getContext(), year + "年" + month + "月" + day + "日：你的日程是：" + scheme, Toast.LENGTH_SHORT).show();
        }
        Log.e("onDateSelected"," -- "+ calendar.getYear() +
                " -- "+ calendar.getMonth() +
                " -- "+ calendar.getDay() +
                " -- "+ isClick +
                " -- "+ calendar.getScheme());
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    //载入数据
    public void initData(){
        int year = mCalendarView.getCurYear();
        int month = mCalendarView.getCurMonth();
        Map<String, Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year,month,3,0xFF40db25,"假").toString(),
                getSchemeCalendar(year,month,3,0xFF40db25,"假"));
        map.put(getSchemeCalendar(year,month,6,0xFFe69138,"事").toString(),
                getSchemeCalendar(year,month,6,0xFFe69138,"事"));
        map.put(getSchemeCalendar(year,month,9,0xFFdf1356,"议").toString(),
                getSchemeCalendar(year,month,9,0xFFdf1356,"议"));
        map.put(getSchemeCalendar(year,month,13,0xFFedc56d,"记").toString(),
                getSchemeCalendar(year,month,13,0xFFedc56d,"记"));
        map.put(getSchemeCalendar(year,month,14,0xFFedc56d,"记").toString(),
                getSchemeCalendar(year,month,14,0xFFedc56d,"记"));
        map.put(getSchemeCalendar(year,month,15,0xFFaacc44,"假").toString(),
                getSchemeCalendar(year,month,15,0xFFaacc44,"假"));
        map.put(getSchemeCalendar(year,month,18,0xFFbc13f0,"记").toString(),
                getSchemeCalendar(year,month,18,0xFFbc13f0,"记"));
        map.put(getSchemeCalendar(year,month,22,0xFFdf1356,"议").toString(),
                getSchemeCalendar(year,month,22,0xFFdf1356,"议"));
        map.put(getSchemeCalendar(year,month,25,0xFF13acf0,"假").toString(),
                getSchemeCalendar(year,month,25,0xFF13acf0,"假"));
        map.put(getSchemeCalendar(year,month,27,0xFF13acf0,"多").toString(),
                getSchemeCalendar(year,month,27,0xFF13acf0,"多"));
        mCalendarView.setSchemeDate(map);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text){
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色，则使用这个颜色
        calendar.setScheme(text);
        calendar.addScheme(color,"假");
        calendar.addScheme(day % 2 == 0? 0xDD00CD00 : 0xFFD15FEF, "节");
        calendar.addScheme(day % 2 == 0? 0xFF660000 : 0xFF4169E1, "记");
        return calendar;
    }

}