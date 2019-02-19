package com.example.lucky.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatButton;
import android.view.MotionEvent;

/**
 * Created by lucky on 2019/1/23.
 */

public class MyButton extends AppCompatButton {
    private int cx, cy;
    private int radius;
    private Paint mPaint;
    private int paintColor;

    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setPaintColor(int paintColor) {
        //this.paintColor = paintColor;
        mPaint.setColor(paintColor);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.setText("");
        this.setBackgroundColor(Color.TRANSPARENT);
        int width = getWidth();
        int height = getHeight();
        cx = width / 2;
        cy = height / 2;
        radius = cx < cy ? cx - 10 : cy - 10;
        canvas.drawCircle(cx, cy, radius, mPaint);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLACK);
                return true;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.WHITE);
                return true;
        }
        return super.onTouchEvent(event);
    }
}
