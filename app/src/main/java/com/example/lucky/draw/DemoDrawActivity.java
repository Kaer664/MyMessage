package com.example.lucky.draw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class DemoDrawActivity extends AppCompatActivity {

    private MyView2 view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=new MyView2(this);
        setContentView(view);
        new Thread(view).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.setFlag(false);
    }
}
