package com.example.android_cjj118.ui.me.calendar;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

import java.util.List;

public class FullMonthView extends MonthView {

    private Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //自定义魅族日历布局
    private  Paint mSchemeBasicPaint = new Paint();

    public FullMonthView(Context context) {
        super(context);

        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(dipToPx(context,0.5f));
        mRectPaint.setColor(0x88efefef);

        mSchemeBasicPaint.setAntiAlias(true);
        mSchemeBasicPaint.setStyle(Paint.Style.FILL);
        mSchemeBasicPaint.setTextAlign(Paint.Align.CENTER);
        mSchemeBasicPaint.setFakeBoldText(true);

        //对兼容性进行处理
        //兼容硬件加速无效的代码
        setLayerType(View.LAYER_TYPE_SOFTWARE,mSchemeBasicPaint);
        //4.0以上硬件加速会导致无效
        mSchemeBasicPaint.setMaskFilter(new BlurMaskFilter(50,BlurMaskFilter.Blur.SOLID));
    }

    /**
     * 绘制选中的日子
     * */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        canvas.drawRect(x , y,x+mItemWidth,y+mItemHeight,mSelectedPaint);
        return true;
    }

    /**
     * 绘制标记的事件日子
     * */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        mSchemeBasicPaint.setColor(calendar.getSchemeColor());
        List<Calendar.Scheme> schemes = calendar.getSchemes();
        if(schemes == null || schemes.size() == 0){
            return;
        }
        int space =dipToPx(getContext(),2);
        int indexY = y + mItemHeight -2 * space;
        int sw = dipToPx(getContext(),mItemWidth / 10);
        int sh = dipToPx(getContext(),4);
        for (Calendar.Scheme scheme: schemes) {
            mSchemePaint.setColor(scheme.getShcemeColor());
            canvas.drawRect(x+mItemWidth - sw - 2 * space,
                    indexY - sh,
                    x + mItemWidth - 2 * space,
                    indexY,
                    mSchemePaint);
            indexY = indexY - space - sh;
        }
    }

    /**
     * 绘制文本
     * */
    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        canvas.drawRect(x,y,x + mItemWidth,y+mItemHeight,mRectPaint);
        int cx = x + mItemWidth / 2;
        int top = y - mItemHeight / 6;
        boolean isInRange = isInRange(calendar);
        if(isSelected){
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    mSelectTextPaint);
            canvas.drawText(calendar.getLunar(), cx, mTextBaseLine + y + mItemHeight /10 ,
                    mSelectedLunarTextPaint);
        }else if (hasScheme){
            canvas.drawText(String.valueOf(calendar.getDay()), cx, mTextBaseLine + top,
                    calendar.isCurrentMonth() && isInRange ? mSchemeTextPaint : mOtherMonthTextPaint);
            canvas.drawText(calendar.getLunar(),cx,mTextBaseLine + y + mItemHeight / 10,
                    mCurMonthLunarTextPaint);
        }else{
            canvas.drawText(String.valueOf(calendar.getDay()),cx,mTextBaseLine + top,
                    calendar.isCurrentDay() ? mCurDayTextPaint :
                    calendar.isCurrentMonth() && isInRange ? mCurMonthTextPaint: mOtherMonthTextPaint);
            canvas.drawText(calendar.getLunar(),cx,mTextBaseLine + y + mItemHeight / 10,
                    calendar.isCurrentDay() && isInRange ? mCurDayLunarTextPaint:
                    calendar.isCurrentMonth() ? mCurMonthLunarTextPaint : mOtherMonthLunarTextPaint);
        }
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
