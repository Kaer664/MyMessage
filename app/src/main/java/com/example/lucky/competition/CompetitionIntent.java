package com.example.lucky.competition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lucky.mymessage.R;

public class CompetitionIntent extends AppCompatActivity {

    private Button btnC1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_intent);
    }

    public void intent(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnC1:
                intent=new Intent(this,ETCAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.btnC2:
                intent=new Intent(this,BillActivity.class);
                startActivity(intent);
                break;
            case R.id.btnC3:
                intent=new Intent(this,TrafficTimeActivity.class);
                startActivity(intent);
                break;
            case R.id.btnC4:
                intent=new Intent(this,TActivity.class);
                startActivity(intent);
                break;
            case R.id.btnC5:
                intent=new Intent(this,SQLAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.btnC6:
                intent=new Intent(this,EnvironmentalIndicatorsActivity.class);
                startActivity(intent);
                break;
            case R.id.btnC7:
                intent=new Intent(this,CarBureauActivity.class);
                startActivity(intent);
                break;
            case R.id.btnC8:
                intent=new Intent(this,PublicTransportQueryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
