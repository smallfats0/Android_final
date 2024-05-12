package com.example.android_cjj118.ui.me.graffiti;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.android_cjj118.R;


public class HandWrite extends View {
    Paint paint;//定义画笔
    Bitmap origBit;//存放初始图像
    Bitmap new_1Bit;//存放从原始图像复制的位图图像
    Bitmap new_2Bit;//存放处理后的图像
    float startX = 0, startY = 0;//画线的起点坐标
    float clickX = 0, clickY = 0;//画线的终点坐标
    boolean isMove = true;//设置是否画线的标记
    boolean isClear = false;//设置是否清除涂鸦的标记
    int color = Color.RED;//画笔颜色
    float strokeWidth = 4.0f;//设置画笔的宽度

    public HandWrite(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //从资源中获取原始图像
        //后续扩展可以从互联网上下图
        origBit = BitmapFactory.decodeResource(getResources(), R.drawable.migon).copy(Bitmap.Config.ARGB_8888,true);

        //建立原始图像的位图
        new_1Bit = Bitmap.createBitmap(origBit);
    }
    //更换图片
    public void setBitmap(Bitmap bitmap){
        origBit = bitmap.copy(Bitmap.Config.ARGB_8888,true);
        new_1Bit = Bitmap.createBitmap(origBit);
        invalidate();
    }
    //清除涂鸦
    public void clear(){
        isClear = true;
        new_2Bit = Bitmap.createBitmap(origBit);
        invalidate();
    }

    //画笔宽度
    //以后可扩展
    public void setSyle(float strokeWidth){
        this.strokeWidth = strokeWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(HandWriting(new_1Bit),0,0,null);
    }

    //记录绘制图形
    public Bitmap HandWriting(Bitmap newBit){
        Canvas canvas = null;//定义画布
        if(isClear){
            canvas = new Canvas(new_2Bit);
        }else {
            canvas = new Canvas(newBit);
        }
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);

        if(isMove){
            canvas.drawLine(startX,startY,clickX,clickY,paint);//在画布上画线条
        }
        startX = clickX;
        startY = clickY;

        if(isClear){
            return new_2Bit;//返回新绘画的图像
        }
        return newBit;//若清屏则返回原图像
    }

    //定义触屏事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        clickX = event.getX();//获取触摸坐标的位置
        clickY = event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN){//按下屏幕但无绘画
            isMove = false;
            invalidate();
            return true;
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){//记录在屏幕上划动的轨迹
            isMove = true;
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
