package com.example.lucky.draw;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lucky.mymessage.R;

/**
 * Created by lucky on 2019/1/23.
 */

public class CompositeControlView extends LinearLayout {
    public CompositeControlView(Context context) {
        super(context);
        this.addView(inflater(context));
    }




    public CompositeControlView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public View inflater(Context context){
        LayoutInflater inflater=LayoutInflater.from(context);
        return inflater.inflate(R.layout.composite_view,null);
    }
}
