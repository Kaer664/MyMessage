package com.example.lucky.draw;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lucky.mymessage.R;

/**
 * Created by lucky on 2019/2/15.
 */

public class CompositeView extends LinearLayout{


    public CompositeView(Context context) {
        super(context);
        View view=inf(context);
        this.addView(view);
    }

    public CompositeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view=inf(context);
        this.addView(view);
    }

    public CompositeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view=inf(context);
        this.addView(view);
    }

    public View inf(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_linear_layout,null);
        return view;
    }
}
