package com.example.lucky.mymessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class AddDataGrid extends AppCompatActivity {

    private Button btn_add_student;
    private EditText et_student_name;
    private GridView grid_view_student_names;
    private Listen listen=new Listen();
    private ArrayAdapter<String> adapter;
    private List<String> student_names=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_grid);
        btn_add_student= (Button) findViewById(R.id.btn_add_student);
        et_student_name= (EditText) findViewById(R.id.et_student_name);
        grid_view_student_names= (GridView) findViewById(R.id.grid_view_student_names);
        btn_add_student.setOnClickListener(listen);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,student_names);
        grid_view_student_names.setAdapter(adapter);
    }

    private class Listen implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String name=et_student_name.getText().toString().trim();
            student_names.add(name);
            adapter.notifyDataSetChanged();
        }
    }
}
