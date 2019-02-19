package com.example.lucky.life;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lucky.mymessage.R;

public class Lifectivity extends AppCompatActivity {

    TextView disTv;
    Button btnIs;
    private int count=0;
    private boolean mainFlag;
    private boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifectivity);
        Log.i("TestNum", "onCreate");
        disTv = (TextView) findViewById(R.id.disTv);
        btnIs = (Button) findViewById(R.id.btnIs);
        btnIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPause){
                    isPause=false;
                }else{
                    isPause=true;
                }
            }
        });
        new Thread(){
            @Override
            public void run() {
                mainFlag=true;
                while (mainFlag){
                    if(!isPause){
                        count++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                disTv.setText("值为："+count+"");
                            }
                        });
                    }
                }
            }
        }.start();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onStart() {
        Log.i("TestNum", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        //显示，获得焦点，activity可见
        Log.i("TestNum", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        //失去焦点  可见，不可以进行任何操作
        super.onPause();
        Log.i("TestNum", "onPause");
    }

    @Override
    protected void onStop() {
        //activity遮盖掉了  ，不可见了
        super.onStop();
        isPause=true;
        Log.i("TestNum", "onStop");
    }

    //之调用一次
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainFlag=false;
        Log.i("TestNum", "onDestroy");
    }
}
