package com.example.lucky.competition;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lucky.mymessage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicTransportQueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_transport_query);
        setActionBar();
        setSpinner();
    }

    private Spinner tSpinner1;
    private void setSpinner() {
        tSpinner1=(Spinner) findViewById(R.id.tSpinner1);
        List<Map<String,String>> data=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("peopleNum","101");
        map.put("comeMinutes","5");
        map.put("distance","100");
        data.add(map);

        Map<String,String> map2=new HashMap<>();
        map2.put("peopleNum","101");
        map2.put("comeMinutes","6");
        map2.put("distance","1000");
        data.add(map);
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.transport_spinner_item,new String[]{"peopleNum","comeMinutes","distance"},new int[]{R.id.peopleNum,R.id.comeMinutes,R.id.distance});
        //adapter.setDropDownViewResource();
        tSpinner1.setAdapter(adapter);
    }


    /**
     * 此方法创建了ActionBar
     */
    private void setActionBar() {
        ActionBar v7ActionBar=getSupportActionBar();
        if(v7ActionBar!=null){
            View public_tqa_bar= LayoutInflater.from(this).inflate(R.layout.top_bar_item,null);
            v7ActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            v7ActionBar.setCustomView(public_tqa_bar);
            ImageView imgBar= (ImageView) public_tqa_bar.findViewById(R.id.img_bar);
            imgBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PublicTransportQueryActivity.this,"点击了顶部图片",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
