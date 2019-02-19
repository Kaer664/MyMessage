package com.example.lucky.competition.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lucky.mymessage.R;

/**
 * Created by lucky on 2019/2/15.
 */

public class MyGroupView extends RelativeLayout{

    public TextView tvSign,tvRound;
    public MyGroupView(Context context) {
        super(context);
        this.addView(inf(context));
    }

    public MyGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.addView(inf(context));
    }

    public MyGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.addView(inf(context));
    }

    public View inf(Context context){
        View view=LayoutInflater.from(context).inflate(R.layout.my_linear_layout,null);
        tvSign= (TextView) view.findViewById(R.id.tvSign);
        tvRound= (TextView) view.findViewById(R.id.tvRound);
        return view;
    };
}
