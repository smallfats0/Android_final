package com.example.android_cjj118.ui.me.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.YearView;

/**
 * 自定义年视图*/

public class CustomYearView extends YearView {
    private int mTextPadding;
    /**
     * 闰年字体*/
    private Paint mLeapYearTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomYearView(Context context) {
        super(context);
        mTextPadding=dipToPx(context,3);
        mLeapYearTextPaint.setTextScaleX(dipToPx(context,12));
        mLeapYearTextPaint.setColor(0xffd1d1d1);
        mLeapYearTextPaint.setAntiAlias(true);
        mLeapYearTextPaint.setFakeBoldText(true);//是否伪粗体
    }

    @Override
    protected void onDrawMonth(Canvas canvas, int year, int month, int x, int y, int width, int height) {
        String text = getContext()
                .getResources()
                .getStringArray(com.haibin.calendarview.R.array.month_string_array)[month - 1];
        canvas.drawText(text,
                x + mItemWidth / 2 - mTextPadding,
                y + mMonthTextBaseLine,
                mMonthTextPaint);
        if(month==2 && isLeapYear(year)) {//判断闰年
            float w = getTextWidth(mMonthTextPaint,text);
            canvas.drawText("闰年",
                    x + mItemWidth / 2 - mTextPadding + w + dipToPx(getContext(),6),
                    y + mMonthTextBaseLine,
                    mLeapYearTextPaint);
        }
    }

    @Override
    protected void onDrawWeek(Canvas canvas, int week, int x, int y, int width, int height) {
        String text =
                getContext().getResources().getStringArray(com.haibin.calendarview.R.array.year_view_week_string_array)[week];
        canvas.drawText(text,
                x + width / 2,
                y + mWeekTextBaseLine,
                mWeekTextPaint);
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight / 2;
        int radius = Math.min(mItemWidth,mItemHeight) / 8 *5;
        canvas.drawCircle(cx,cy,radius,mSelectedPaint);
        return true;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {

    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;
        if(isSelected){
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    hasScheme ? mSchemeTextPaint : mSelectTextPaint);
        }else if(hasScheme){
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint : mSchemeTextPaint);
        }else{
            canvas.drawText(String.valueOf(calendar.getDay()),
                    cx,
                    baselineY,
                    calendar.isCurrentDay() ? mCurDayTextPaint : mCurMonthTextPaint);
        }
    }

    /**
     * 获取文本长度*/
    private float getTextWidth(Paint paint,String text){
        return paint.measureText(text);
    }


    /**
     * 判断闰年*/
    private static boolean isLeapYear(int year){
        return ((year% 4 ==0) && (year % 100 !=0)) || (year % 400 ==0);
    }

    /**
     * dp转px
     *
     * @Param context context
     * @Param dpValue dp
     * @return px
     * */
    private static int dipToPx(Context context,float dpValue){
        // https://blog.csdn.net/lgzaaron/article/details/52517941 代码参考
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale +0.5f);
    }
}
