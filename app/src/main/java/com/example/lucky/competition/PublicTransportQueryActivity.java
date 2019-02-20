package com.example.lucky.competition;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.mymessage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicTransportQueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_transport_query);
        setActionBar();
        setSpinner();
    }

    private Spinner tSpinner1;

    private void setSpinner() {
        tSpinner1= (Spinner) findViewById(R.id.tSpinner1);
        tSpinner1.setAdapter((SpinnerAdapter) new TAdapter(this));
    }

    class TAdapter extends BaseExpandableListAdapter {
        private Context context;
        private String[] group = {"衣服", "食品", "水果"};
        private String[] cloths = {"衬衫", "西裤", "内衣"};
        private String[] food = {"面包", "蛋糕", "可乐", "橙汁"};
        private String[] fruits = {"苹果", "香蕉", "草莓"};

        private List<String> groupList = null;
        private List<List<String>> itemList = null;

        protected TAdapter(Context context) {
            this.context = context;
            groupList = new ArrayList<String>();
            itemList = new ArrayList<List<String>>();
            setListData();
        }

        /**
         * 向list中添加数据
         */
        private void setListData() {
            //添加商品类型数据这里是三种类
            for (String s : group) {
                groupList.add(s);
            }

            //声明各个品种商品
            List clothsList = new ArrayList();
            List foodList = new ArrayList();
            List fruitsList = new ArrayList();

            //开始给商品添加数据
            for (String s : cloths) {
                clothsList.add(s);
            }
            for (String s : food) {
                foodList.add(s);
            }
            for (String s : fruits) {
                fruitsList.add(s);
            }

            itemList.add(clothsList);
            itemList.add(foodList);
            itemList.add(fruitsList);
        }

        @Override
        public int getGroupCount() {
            //返回组数量
            return itemList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            //返回当前组的节点数量
            return itemList.get(groupPosition).size();
        }

        /**
         *
         * @param groupPosition  父容器
         * @return 父容器的一个实例
         */
        @Override
        public Object getGroup(int groupPosition) {
            //返回分组对象，用于一些数据传递，在事件处理时可直接取得和分组相关的数据
            return groupList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            //设置子节点数据
            return itemList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            //设置组显示View，这里可是设置布局文件引用
            TextView view = null;
            if (convertView == null) {
                view = new TextView(context);
            } else {
                view = (TextView) convertView;
            }
            //设置textView的显示样式
            view.setTextSize(30);
            view.setPadding(50, 5, 0, 5);
            //向textView设置数据
            String s = groupList.get(groupPosition);
            view.setText(s);
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            //设置节点显示View，这里可是设置布局文件引用
            TextView view = null;
            if (convertView == null) {
                view = new TextView(context);
            } else {
                view = (TextView) convertView;
            }
            view.setTextSize(20);
            view.setPadding(30, 5, 0, 5);
            //向textView设置数据
            String s = itemList.get(groupPosition).get(childPosition);
            view.setText(s);
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }


    class Adapter extends BaseExpandableListAdapter {

        private int[] images = new int[]{
                R.drawable.busimg,
                R.drawable.busimg,
                R.drawable.busimg
        };
        private String[] armTypes = new String[]{
                "神族", "虫族", "人族"
        };
        private String[][] arms = new String[][]{
                {"狂战士", "龙骑士", "黑暗圣堂"},
                {"小狗", "飞龙", "自爆妃子"},
                {"步兵", "伞兵", "护士mm"}
        };

        @Override
        public int getChildrenCount(int i) {
            return arms[i].length;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }


        @Override
        public Object getGroup(int i) {
            return armTypes[i];
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }

        @Override
        public int getGroupCount() {
            return armTypes.length;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }

    /**
     * 此方法创建了ActionBar
     */
    private void setActionBar() {
        ActionBar v7ActionBar = getSupportActionBar();
        if (v7ActionBar != null) {
            View public_tqa_bar = LayoutInflater.from(this).inflate(R.layout.top_bar_item, null);
            v7ActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            v7ActionBar.setCustomView(public_tqa_bar);
            ImageView imgBar = (ImageView) public_tqa_bar.findViewById(R.id.img_bar);
            imgBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(PublicTransportQueryActivity.this, "点击了顶部图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
