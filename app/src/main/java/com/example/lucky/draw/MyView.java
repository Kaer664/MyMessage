package com.example.lucky.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lucky.mymessage.R;

/**
 * Created by lucky on 2019/1/22.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private Paint mPaint;

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);  //设置单线条
        mPaint.setStrokeWidth(3);   //设置线条的宽度
        mbitmap= BitmapFactory.decodeResource(getResources(), R.drawable.img1);
        mbitmap=Bitmap.createScaledBitmap(mbitmap,100,100,true);
    }


    private Bitmap mbitmap;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //x轴y轴半径 画笔
        canvas.drawCircle(50, 50, 50, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(10,120,110,170,mPaint);
        canvas.drawBitmap(mbitmap,10,200,null);
    }
}
