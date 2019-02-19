package com.example.lucky.competition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucky.competition.view.MyGroupView;
import com.example.lucky.mymessage.R;

public class EnvironmentalIndicatorsActivity extends AppCompatActivity {

    private MyGroupView MGroupView1,MGroupView2,MGroupView3,MGroupView4,MGroupView5,MGroupView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_indicators);
        init();
    }

    private void init() {
        MGroupView1= (MyGroupView) findViewById(R.id.MGroupView1);
        MGroupView2= (MyGroupView) findViewById(R.id.MGroupView2);
        MGroupView3= (MyGroupView) findViewById(R.id.MGroupView3);
        MGroupView4= (MyGroupView) findViewById(R.id.MGroupView4);
        MGroupView5= (MyGroupView) findViewById(R.id.MGroupView5);
        MGroupView6= (MyGroupView) findViewById(R.id.MGroupView6);
        MGroupView1.tvSign.setText("20");
        MGroupView2.tvSign.setText("20");
        MGroupView3.tvSign.setText("20");
        MGroupView4.tvSign.setText("20");
        MGroupView5.tvSign.setText("20");
        MGroupView6.tvSign.setText("20");

    }
}
