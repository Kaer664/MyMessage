package com.example.lucky.competition;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.competition.sql.MyOpenHelp;
import com.example.lucky.mymessage.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ETCAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etBalance, etRecharge;
    private Spinner spinnerCarNum;
    private Button btnCheck, btnRecharge;
    private ListView listView1;
    private static final String USER="admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etcaccount);
        etBalance = (EditText) findViewById(R.id.etBalance);
        etRecharge = (EditText) findViewById(R.id.etRecharge);
        spinnerCarNum = (Spinner) findViewById(R.id.spinnerCarNum);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnRecharge = (Button) findViewById(R.id.btnRecharge);
        listView1 = (ListView) findViewById(R.id.listView1);
        btnCheck.setOnClickListener(this);
        btnRecharge.setOnClickListener(this);
        myOpenHelp=new MyOpenHelp(ETCAccountActivity.this,"carAccount.db",null,15);
        setListener();
    }

    private void setListener() {
        spinnerCarNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etRecharge.addTextChangedListener(new TextWatcher() {
            Integer inputNum;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {} else {
                    inputNum = Integer.parseInt(charSequence.toString());
                    if (inputNum >= 1 && inputNum <= 999) {
                    } else {
                        Toast.makeText(ETCAccountActivity.this, "请输入1到999之间的整数！", Toast.LENGTH_SHORT).show();
                        etRecharge.setText("");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    private MyOpenHelp myOpenHelp;
    private SQLiteDatabase db;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCheck:
                break;
            case R.id.btnRecharge:
                db=myOpenHelp.getReadableDatabase();
                String money=etRecharge.getText().toString(); //充值金额
                String carNum=spinnerCarNum.getSelectedItem().toString();  //小车号
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
                String date=format.format(System.currentTimeMillis());  //时间
                db.execSQL("insert into account(carNum,money,user,timeDate)values(" +
                        "'" +carNum+"',"+
                        "'" +money+"',"+
                        "'" +USER+"',"+
                        "'" +date+"'"+
                        ")");
                Toast.makeText(ETCAccountActivity.this,"数据库插入数据成功!",Toast.LENGTH_SHORT).show();
                Cursor cursor=db.query("account",null,null,null,null,null,null,null);
                cursor.moveToFirst();
                cursor.moveToLast();
                etBalance.setText(cursor.getString(cursor.getColumnIndex("money")));
                break;
        }

    }

}
