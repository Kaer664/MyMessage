package com.example.lucky.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lucky on 2019/2/15.
 */

public class MyRoundView extends View{

    public MyRoundView(Context context) {
        super(context);
        init();
    }

    public MyRoundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    Paint mPaint;
    private void init() {
        mPaint=new Paint();
        mPaint.setColor(Color.GRAY);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
