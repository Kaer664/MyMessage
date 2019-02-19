package com.example.lucky.competition;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.competition.sql.StudentOpenHelper;
import com.example.lucky.mymessage.R;

public class TActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRed, btnTest, btnNext, btnPrevious, btnFirst, btnLast ,btnDelete,btnUpDate;
    private EditText etName, etPwd;
    private ListView listViewDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t);
        init();
    }

    /**
     * 初始化控件方法
     */
    private void init() {
        btnRed = (Button) findViewById(R.id.btnRed);
        btnTest = (Button) findViewById(R.id.btnTest);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnLast = (Button) findViewById(R.id.btnLast);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpDate = (Button) findViewById(R.id.btnUpDate);

        etName = (EditText) findViewById(R.id.etName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        listViewDB = (ListView) findViewById(R.id.listViewDB);
        btnRed.setOnClickListener(this);
        btnTest.setOnClickListener(this);

        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnFirst.setOnClickListener(this);
        btnLast.setOnClickListener(this);

        btnDelete.setOnClickListener(this);
        btnUpDate.setOnClickListener(this);
        studentDB = new StudentOpenHelper(this, "Student.db", null, 3);
    }

    private StudentOpenHelper studentDB;
    private SQLiteDatabase db;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnTest:
                if (insertData() != -1) {
                    Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "插入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRed:
                queryData();
                break;
            case R.id.btnNext:
                nextData();
                break;
            case R.id.btnPrevious:
                previousData();
                break;
            case R.id.btnFirst:
                firstData();
                break;
            case R.id.btnLast:
                lastData();
                break;
            case R.id.btnDelete:
                deleteData();
                break;
            case R.id.btnUpDate:
                upDate();
                break;
        }
    }

    private void upDate() {
        String id=cursor.getColumnName(cursor.getColumnIndex("_id"));
        SQLiteDatabase db=studentDB.getReadableDatabase();
        ContentValues values=new ContentValues();
        String name=etName.getText().toString().trim();
        String pwd=etPwd.getText().toString().trim();
        values.put("name",name);
        values.put("pwd",pwd);
        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
        int line=db.update("student",values,"_id=?",new String[]{id});
        if(line>0){
            Toast.makeText(this,"更新成功...",Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData() {
        String id=cursor.getColumnName(cursor.getColumnIndex("_id"));
        SQLiteDatabase db=studentDB.getReadableDatabase();
        int line=db.delete("student","_id=?",new String[]{id});
        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
        if(line>0){
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
        }
    }

    private void lastData() {
        if (cursor.moveToLast()) {
            displayData();
        }
    }

    private void firstData() {
        if (cursor.moveToFirst()) {
            displayData();
        }
    }

    private void previousData() {
        if (cursor.moveToPrevious()) {
            displayData();
        }
    }

    Cursor cursor;

    private void nextData() {
        if (cursor.moveToNext()) {
            displayData();
        }
    }

    private void displayData() {
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
        etName.setText(name);
        etPwd.setText(pwd);
    }

    private void queryData() {
        db = studentDB.getReadableDatabase();
        cursor = db.query("student", null, null, null, null, null, null);

//        CursorAdapter adapter=new CursorAdapter(this,cursor,2) {
//            @Override
//            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
//                View findView=LayoutInflater.from(context).inflate(R.layout.test_db_item,null);
//                return findView;
//            }
//
//            @Override
//            public void bindView(View view, Context context, Cursor cursor) {
//                TextView tv1= (TextView) view.findViewById(R.id.tv1);
//                TextView tv2= (TextView) view.findViewById(R.id.tv2);
//                String name=cursor.getString(cursor.getColumnIndex("name"));
//                String pwd=cursor.getString(cursor.getColumnIndex("pwd"));
//                tv1.setText(name);
//                tv2.setText(pwd);
//            }
//        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.test_db_item, cursor, new String[]{"name", "pwd"}, new int[]{R.id.tv1, R.id.tv2, R.id.tv3}, 2);
        listViewDB.setAdapter(adapter);
    }

    private Long insertData() {
        String name = etName.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        db = studentDB.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("pwd", pwd);
        Long is = db.insert("student", null, values);  //插入不成功返回-1
        return is;
    }
}
