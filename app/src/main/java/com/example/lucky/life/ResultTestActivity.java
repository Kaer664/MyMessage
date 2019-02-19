package com.example.lucky.life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.lucky.mymessage.R;

public class ResultTestActivity extends AppCompatActivity {

    private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tv1= (TextView) findViewById(R.id.tv1);
    }
    public void intent1(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.btnResult:
                intent =new Intent(this,SecondActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("num1",10);
                bundle.putInt("num2",20);
                intent.putExtras(bundle);
                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        Log.i("TestNum","startActivityForResult");
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TestNum","onActivityResult");
        if(requestCode==100&&resultCode==0x002){
           tv1.setText( "返回值"+data.getIntExtra("nums",-1));
            Log.i("TestNum",""+data.getIntExtra("nums",1));
        }
    }
}
