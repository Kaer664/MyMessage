package com.example.lucky.mymessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerTest extends AppCompatActivity {

    private Spinner spinner1, spinnerGrade, spinnerStudent;
    private Button btn1;
    private String[] data = {"AAA", "BBB", "CCC", "DDD", "EEE", "FFF"};

    private List<String> gradeList = new ArrayList<>();
    private Map<String, List<String>> studentMap = new HashMap<>();
    private List<String> selectStudent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_test);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinnerGrade = (Spinner) findViewById(R.id.spinnerGrade);
        spinnerStudent = (Spinner) findViewById(R.id.spinnerStudent);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        gradeList.add("一班");
        gradeList.add("二班");

        List<String> oneGrade = new ArrayList<>();
        oneGrade.add("张三");
        oneGrade.add("李四");

        List<String> twoGrade = new ArrayList<>();
        twoGrade.add("王五");
        twoGrade.add("赵六");

        studentMap.put(gradeList.get(0), oneGrade);
        studentMap.put(gradeList.get(1), twoGrade);
        ArrayAdapter adapter = new ArrayAdapter(SpinnerTest.this, android.R.layout.simple_list_item_1, android.R.id.text1, gradeList);
        spinnerGrade.setAdapter(adapter);
        spinnerGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectStudent.clear();
                String gradeName=gradeList.get(i);
                List<String> studentNames=studentMap.get(gradeName);
                selectStudent.addAll(studentNames);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(SpinnerTest.this,android.R.layout.simple_list_item_1,android.R.id.text1,selectStudent);
                spinnerStudent.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
