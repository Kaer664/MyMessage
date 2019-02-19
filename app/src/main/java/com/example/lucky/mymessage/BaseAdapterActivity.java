package com.example.lucky.mymessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BaseAdapterActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new Adapter());
    }

    class Adapter extends BaseAdapter {
        private String[] names = new String[]{"Dog1", "Dog2", "Dog3", "Dog4", "Dog5", "Dog6"};
        private int[] ids = new int[]{R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1, R.drawable.img1};

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
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(BaseAdapterActivity.this);
            View itemView = inflater.inflate(R.layout.list_item1, viewGroup, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.image);
            TextView textView = (TextView) itemView.findViewById(R.id.txt);
            imageView.setBackgroundResource(ids[i]);
            textView.setText(names[i]);
            return itemView;
        }
    }
}
