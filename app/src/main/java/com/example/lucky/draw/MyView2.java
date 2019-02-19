package com.example.lucky.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lucky.mymessage.R;

/**
 * Created by lucky on 2019/1/23.
 */

public class MyView2 extends View implements Runnable{
    private int x;
    private Bitmap bmpBackground;
    private boolean flag=true;

    public MyView2(Context context) {
        super(context);
        init();
    }

    private void init() {
        bmpBackground = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setFlag(boolean flag){
        this.flag=flag;
    }

    private void moveBackground(Canvas canvas){
        x-=10;
        int x2=bmpBackground.getWidth()+x;
        if(x2<=0){
            x=0;
            canvas.drawBitmap(bmpBackground,x,10,null);
        }else{
            canvas.drawBitmap(bmpBackground,x,10,null);
            canvas.drawBitmap(bmpBackground,x2,10,null);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        moveBackground(canvas);
    }

    @Override
    public void run() {
        while (flag) {
            postInvalidate();
        }
    }
}
