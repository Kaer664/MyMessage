package com.example.lucky.mymessage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private GridView gridView;
    private Button btnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView= (GridView) findViewById(R.id.gridView1);
        btnIntent= (Button) findViewById(R.id.intent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,AddDataGrid.class);
                startActivity(intent);
            }
        });
        gridView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        private int[] imgs=new int[]{R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,
                R.drawable.img1,R.drawable.img1,R.drawable.img1,R.drawable.img1,};
        private String[]names=new String[]{"Dog1","Dog2","Dog3","Dog4","Dog5","Dog6","Dog7","Dog8",};
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;  //返回项的id
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1;
            ViewHolder holder; //持有者
            if(view==null){
                view1= LayoutInflater.from(Main2Activity.this).inflate(R.layout.grid_item1,null);
                holder=new ViewHolder();
                holder.image= (ImageView) view1.findViewById(R.id.img);
                holder.textView= (TextView) view1.findViewById(R.id.txt);
                view1.setTag(holder);
            }else{
                view1=view;
                holder= (ViewHolder) view1.getTag();
            }
            ImageView imageView=holder.image;
            TextView textView=holder.textView;
            imageView.setBackgroundResource(imgs[i]);
            textView.setText(names[i]);
            return view1;
        }

        private class ViewHolder{
            ImageView image;
            TextView textView;
        }
    }
}
