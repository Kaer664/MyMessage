package com.example.lucky.competition;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lucky.competition.sql.MyOpenHelp;
import com.example.lucky.mymessage.R;

public class SQLAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinner_time;
    private Button btn_query;
    private ListView listView_showl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlaccount);
        hint();
    }

    private void hint() {
        spinner_time = (Spinner) findViewById(R.id.spinner_time);
        btn_query = (Button) findViewById(R.id.btn_query);
        listView_showl = (ListView) findViewById(R.id.listView_show);
        myOpenHelp = new MyOpenHelp(this, "carAccount.db", null, 15);
        db = myOpenHelp.getReadableDatabase();
        btn_query.setOnClickListener(this);
        int num= 2/1>2?10:50;

        spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sign) {
                    String select=spinner_time.getSelectedItem().toString();
                    if(select.equals("时间升序")){
                        cursor=db.rawQuery("select * from account order by timeDate asc",new String[]{});
                        SimpleCursorAdapter adapter = new SimpleCursorAdapter(SQLAccountActivity.this, R.layout.account_show_item,
                                cursor, new String[]{"_id", "carNum", "money", "user", "timeDate"}, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5}, 2);
                        listView_showl.setAdapter(adapter);
                    }else{
                        cursor=db.rawQuery("select * from account order by timeDate desc",new String[]{});
                        SimpleCursorAdapter adapter = new SimpleCursorAdapter(SQLAccountActivity.this, R.layout.account_show_item,
                                cursor, new String[]{"_id", "carNum", "money", "user", "timeDate"}, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5}, 2);
                        listView_showl.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private MyOpenHelp myOpenHelp;
    Cursor cursor;
    private SQLiteDatabase db;

    private boolean sign;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                sign = true;
                cursor=db.rawQuery("select * from account order by timeDate desc",new String[]{});
                cursor.moveToFirst();
                int num = cursor.getCount();
                if (num != 0) {
                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(SQLAccountActivity.this, R.layout.account_show_item,
                            cursor, new String[]{"_id", "carNum", "money", "user", "timeDate"}, new int[]{R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5}, 2);
                    listView_showl.setAdapter(adapter);
                } else {
                    Toast.makeText(SQLAccountActivity.this, "是空的NULL", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
