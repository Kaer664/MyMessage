package com.example.lucky.mymessage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;
    private Button btnAsync;
    private Handler handler2 = new Handler() {
        //需要更改ui所以在主线程中键创建
        @Override
        public void handleMessage(Message msg) {
            //在这个方法中处理消息，根据相应的what值进行处理
            switch (msg.what) {
                case 0x001:
                    tvTest.setText(msg.arg1 + "");  //更改主线程中的ui
                    break;
            }
        }
    };

    private Handler handler = new Handler() {
        //需要更改ui所以在主线程中键创建
        @Override
        public void handleMessage(Message msg) {
            //在这个方法中处理消息，根据相应的what值进行处理
            switch (msg.what) {
                case 0x001:
                    tvTest.setText(msg.arg1 + "");  //更改主线程中的ui
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTest = (TextView) findViewById(R.id.tvTest);
        btnAsync= (Button) findViewById(R.id.btnAsync);
    }

    public void test(View view) {

        switch (view.getId()) {
            case R.id.btnAsync:
                new Thread() {
                    @Override
                    public void run() {
                        Log.w("ThreadMs", "点击到了线程中");
                        for (int i = 0; i <= 100; i++) {
                            Message msg = new Message();  //使用Message发送消息
                            msg.what = 0x001;    //what是消息的标识，只有唯一的一个  在handleMessage方法中根据对应的what值进行判断处理消息
                            msg.arg1 = i;    //如果需要传递值的话使用arg1与arg2传递整数值
                            handler.sendMessage(msg);  //发送消息
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
                break;
            case R.id.btnThread:
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
