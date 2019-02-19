package com.example.lucky.draw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CompositeControlActivity extends AppCompatActivity {

    private CompositeControlView view;
    private CompositeView view2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=new CompositeControlView(this);
        view2=new CompositeView(this);
        setContentView(view2);
    }
}
