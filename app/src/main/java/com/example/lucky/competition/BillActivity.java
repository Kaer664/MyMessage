package com.example.lucky.competition;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lucky.competition.sql.RoadInfoOpenHelp;
import com.example.lucky.mymessage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillActivity extends AppCompatActivity implements View.OnClickListener {

    private Switch switch1;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x001:
                    Cursor c2=database.rawQuery("select * from road",new String[]{});
                    c2.moveToLast();
                    String humidity=c2.getString(c2.getColumnIndex("humidity"));
                    Notification notification=new Notification.Builder(BillActivity.this)
                            .setSmallIcon(R.drawable.img1).setContentTitle("警告内容")
                            .setContentText(" 湿润警报,阈值：80,当前："+humidity)
                            .setTicker("警告").getNotification();
                    notificationManager.notify(1,notification);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        init();
        switch1 = (Switch) findViewById(R.id.switch1);
        actionBar();
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switch1.setText("开");
                    for (int i = 0; i < ets.size(); i++) {
                        ets.get(i).setEnabled(false);
                    }
                    //之后发送标题栏10s  发送通知
                   new Thread(){
                       @Override
                       public void run() {
                           try {
                               while (true) {
                                   Thread.sleep(100); //停顿十秒钟
                                   Message msg = handler.obtainMessage(0x001);
                                   handler.sendMessage(msg);
                               }
                           } catch (InterruptedException e) {
                               e.printStackTrace();
                           }
                       }
                   }.start();
                } else {
                    queryData();
                }
            }
        });
    }

    /**
     * 查询数据方法
     */
    private void queryData() {
        switch1.setText("关");
        for (int i = 0; i < ets.size(); i++) {
            ets.get(i).setEnabled(true);
        }
        Cursor c1 = database.rawQuery("select * from road", new String[]{});
        if (c1.moveToLast()) {
            String temperature = c1.getString(c1.getColumnIndex("temperature"));
            String humidity = c1.getString(c1.getColumnIndex("humidity"));
            String light = c1.getString(c1.getColumnIndex("light"));
            String co = c1.getString(c1.getColumnIndex("co"));
            String pm = c1.getString(c1.getColumnIndex("pm"));
            String road = c1.getString(c1.getColumnIndex("road"));
            etTemperature.setText(temperature);
            etHumidity.setText(humidity);
            etLight.setText(light);
            etCo.setText(co);
            etPM.setText(pm);
            etRoad.setText(road);
        } else {
            Toast.makeText(BillActivity.this, "没有最后一行数据：id:", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 插入数据方法
     */
    private Long insertData() {
        String temperature = etTemperature.getText().toString();
        String humidity = etHumidity.getText().toString();
        String light = etLight.getText().toString();
        String co = etCo.getText().toString();
        String pm = etPM.getText().toString();
        String road = etRoad.getText().toString();
        ContentValues values = new ContentValues();
        values.put("temperature", temperature);
        values.put("humidity", humidity);
        values.put("light", light);
        values.put("co", co);
        values.put("pm", pm);
        values.put("road", road);
        Long l = database.insert("road", null, values);
        return l;
    }


    private EditText etTemperature, etHumidity, etLight, etCo, etPM, etRoad;
    private Button btnSave;
    private List<EditText> ets = new ArrayList<>();
    private RoadInfoOpenHelp roadHelp;
    private SQLiteDatabase database;
    private NotificationManager notificationManager;

    /**
     * 初始化控件方法
     */
    private void init() {
        etTemperature = (EditText) findViewById(R.id.et1);
        etHumidity = (EditText) findViewById(R.id.et2);
        etLight = (EditText) findViewById(R.id.et3);
        etCo = (EditText) findViewById(R.id.et4);
        etPM = (EditText) findViewById(R.id.et5);
        etRoad = (EditText) findViewById(R.id.et6);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        ets.add(etTemperature);
        ets.add(etHumidity);
        ets.add(etLight);
        ets.add(etCo);
        ets.add(etPM);
        ets.add(etRoad);
        for (int i = 0; i < ets.size(); i++) {
            ets.get(i).setEnabled(false);
        }
        roadHelp = new RoadInfoOpenHelp(this, "roadInfo.db", null, 1);
        database = roadHelp.getReadableDatabase();
        notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * 该方法  调整actionbar  自定义
     */
    private void actionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            int num = actionBar.getHeight();
            View top_action_bar = LayoutInflater.from(this).inflate(R.layout.top_bar_item, null);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(top_action_bar);
            ImageView img = (ImageView) top_action_bar.findViewById(R.id.img_bar);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(BillActivity.this, "Get Ready Return AND Height:" + num, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                if (!switch1.isChecked()) {
                    if (insertData() != -1) {
                        Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请选择开始", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }
    }


