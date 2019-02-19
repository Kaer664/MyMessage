package com.example.lucky.mymessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lucky.competition.CompetitionIntent;
import com.example.lucky.draw.CompositeControlActivity;
import com.example.lucky.draw.DemoDrawActivity;
import com.example.lucky.life.Lifectivity;
import com.example.lucky.life.ResultTestActivity;
import com.example.lucky.notification.NotificationActivity;
import com.example.lucky.page.ViewPageActivity;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
    }

    public void intent(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn1:
                intent = new Intent(IntentActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn2:
                intent = new Intent(IntentActivity.this, Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn3:
                intent = new Intent(IntentActivity.this, AddDataGrid.class);
                startActivity(intent);
                break;
            case R.id.btn4:
                intent = new Intent(IntentActivity.this, SpinnerDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn5:
                intent = new Intent(IntentActivity.this, ProgressDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.btn6:
                intent = new Intent(IntentActivity.this, BaseAdapterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn7:
                intent = new Intent(IntentActivity.this, SpinnerTest.class);
                startActivity(intent);
                break;
            case R.id.btn8:
                intent = new Intent(IntentActivity.this, ResultTestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn9:
                intent = new Intent(IntentActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn10:
                intent = new Intent(IntentActivity.this, DemoDrawActivity.class);
                startActivity(intent);
                break;
            case R.id.btn11:
                intent = new Intent(IntentActivity.this, CompositeControlActivity.class);
                startActivity(intent);
                break;
            case R.id.btn12:
                intent = new Intent(IntentActivity.this, ViewPageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn13:
                intent = new Intent(IntentActivity.this, Lifectivity.class);
                startActivity(intent);
                break;
            case R.id.btn14:
                intent = new Intent(IntentActivity.this, CompetitionIntent.class);
                startActivity(intent);
                break;
    }
    }
}
