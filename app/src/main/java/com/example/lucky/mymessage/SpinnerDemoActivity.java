package com.example.lucky.mymessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerDemoActivity extends AppCompatActivity {

    private String[]data={"AAA","BBB","CCC","DDD","EEE"};
    private Spinner spinner1,spinner2,spinnerGrade,spinnerStudent;

    //班级列表
    private List<String> gradeList=new ArrayList<>();
    //学生列表
    private Map<String,List<String>> studentMap=new HashMap<>();
    //选中的学生列表
    private List<String> selectedStudent=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_demo);
        spinner1= (Spinner) findViewById(R.id.spinner1);
        spinner2= (Spinner) findViewById(R.id.spinner2);

        spinnerGrade= (Spinner) findViewById(R.id.spinnerGrade);
        spinnerStudent= (Spinner) findViewById(R.id.spinnerStudent);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,android.R.id.text1,data);
        spinner1.setAdapter(adapter);

        gradeList.add("Android班");
        gradeList.add("嵌入式班");

        List<String> androidStudent=new ArrayList<>();
        androidStudent.add("张三");
        androidStudent.add("李四");

        List<String> armStudent=new ArrayList<>();
        armStudent.add("王五");
        armStudent.add("赵六");

        studentMap.put(gradeList.get(0),androidStudent);
        studentMap.put(gradeList.get(1),armStudent);

        ArrayAdapter<String> gradeAdapter=new ArrayAdapter<String>(SpinnerDemoActivity.this,
                android.R.layout.simple_list_item_1,android.R.id.text1,gradeList);
        spinnerGrade.setAdapter(gradeAdapter);

        final ArrayAdapter<String> studentAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,android.R.id.text1,selectedStudent);
        spinnerStudent.setAdapter(studentAdapter);

        spinnerGrade
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(SpinnerDemoActivity.this,gradeList.get(i),Toast.LENGTH_SHORT).show();
                        selectedStudent.clear();
                        String selectGrade=gradeList.get(i);
                        selectedStudent.addAll(studentMap.get(selectGrade));
                        studentAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
    }
}
