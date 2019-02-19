package com.example.lucky.life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lucky.mymessage.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public int countNum(int num1,int num2){
        return num1+num2;
    }

    public void result(View view){
       switch (view.getId()){
           case R.id.btnResult:
               Intent intent=getIntent();
               Bundle bundle=intent.getExtras();
               intent.putExtra("nums",countNum(bundle.getInt("num1"),bundle.getInt("num2")));
               setResult(0x002,intent);
               finish();
               break;
       }
    }
}
