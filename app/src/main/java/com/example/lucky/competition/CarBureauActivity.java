package com.example.lucky.competition;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucky.competition.sql.CarBureauHelp;
import com.example.lucky.mymessage.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarBureauActivity extends AppCompatActivity {

    private ListView listViewCarInfo;
    private CarBureauHelp carBureauHelp;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_bureau);
        carBureauHelp = new CarBureauHelp(this, "carBureau.db", null, 3);
        db = carBureauHelp.getReadableDatabase();
        listViewCarInfo = (ListView) findViewById(R.id.listViewCarInfo);
        actionBarSet();
        setAdapter();
    }


    //list  填充的数据
    private List<Map<String, Object>> listCar = new ArrayList<>();

    /**
     * 设置listView 适配器
     */
    private void setAdapter() {
        Map<String, Object> map = new HashMap<>();
        map.put("num", "1");
        map.put("autoLog", R.drawable.autolog);
        map.put("licensePlate", "辽A10001");
        map.put("name", "车主：张三");
        map.put("money", "100");
        listCar.add(map);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("num", "2");
        map2.put("autoLog", R.drawable.autolog);
        map2.put("licensePlate", "辽A10002");
        map2.put("name", "车主：李四");
        map2.put("money", "99");
        listCar.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("autoLog", R.drawable.autolog);
        map3.put("num", "3");
        map3.put("licensePlate", "辽A10003");
        map3.put("name", "车主：王五");
        map3.put("money", "103");
        listCar.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("num", "4");
        map4.put("autoLog", R.drawable.autolog);
        map4.put("licensePlate", "辽A10004");
        map4.put("name", "车主：赵六");
        map4.put("money", "1");
        listCar.add(map4);
        SimpleAdapter adapter = new SimpleAdapter(this, listCar
                , R.layout.car_user_item2, new String[]{"num", "autoLog", "licensePlate", "name", "money"}
                , new int[]{R.id.num, R.id.autoLog, R.id.licensePlate, R.id.name, R.id.money});
        listViewCarInfo.setAdapter(new Adapter());
    }

    /**
     * 设置顶部ActionBar
     */
    private void actionBarSet() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            View bureau_bar = LayoutInflater.from(this).inflate(R.layout.bureau_bar_item, null);
            actionBar.setDisplayOptions(android.app.ActionBar.DISPLAY_SHOW_CUSTOM); //设置样式自定义属性
            actionBar.setCustomView(bureau_bar);
            ImageView tiv = (ImageView) bureau_bar.findViewById(R.id.tiv);
            tiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CarBureauActivity.this, "点击了图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * BaseAdapter适配器  大多数绑定可以各个控件监听
     */
    class Adapter extends BaseAdapter {
        private String[] nums = new String[]{"1", "2", "3", "4"};
        private int[] autoLogs = new int[]{R.drawable.autolog, R.drawable.autolog, R.drawable.autolog, R.drawable.autolog};
        private String[] licensePlates = new String[]{"辽A10001", "辽A10002", "辽A10003", "辽A10004"};
        private String[] names = new String[]{"车主：张三", "车主：李四", "车主：王五", "车主：赵六"};
        private String[] moneys = new String[]{"100", "99", "103", "1"};

        @Override
        public int getCount() {
            return autoLogs.length;
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
            LayoutInflater inflater = LayoutInflater.from(CarBureauActivity.this);
            View itemView = inflater.inflate(R.layout.car_user_item2, viewGroup, false);
            ImageView autoLog = (ImageView) itemView.findViewById(R.id.autoLog);
            TextView licensePlate = (TextView) itemView.findViewById(R.id.licensePlate);
            TextView name = (TextView) itemView.findViewById(R.id.name);
            TextView money = (TextView) itemView.findViewById(R.id.money);
            autoLog.setBackgroundResource(autoLogs[i]);
            licensePlate.setText(licensePlates[i]);
            name.setText(names[i]);
            money.setText(moneys[i]);
            CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            Button btnRecharge = (Button) itemView.findViewById(R.id.btnRecharge);

            /**
             * item 按钮的查询事件
             */
            btnRecharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (selectViewData.size() == 0) {
                        createAlert(itemView);
                    } else {
                        Toast.makeText(CarBureauActivity.this, "不是空的：" + selectViewData.size(), Toast.LENGTH_SHORT).show();
                        createAlert(selectViewData.get("车主：张三"), selectViewData.get("车主：李四")
                                , selectViewData.get("车主：王五"), selectViewData.get("车主：赵六"));
                    }
                }
            });

            /**
             * 设置选中的checkBox 的复选框
             */
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox ctv = (CheckBox) view;
                    if (checkBox.isChecked()) {
                        Toast.makeText(CarBureauActivity.this, "?" + checkBox.isChecked(), Toast.LENGTH_LONG).show();
                        selectViewData.put(name.getText().toString(), itemView);
                    } else {
                        selectViewData.remove(name.getText().toString());
                    }
                }
            });
            if (Integer.parseInt(money.getText().toString()) <= 10) {
                itemView.setBackgroundColor(Color.parseColor("#ffcc00"));
            }
            return itemView;
        }
    }

    /**
     * 按钮单击事件创建Dialog
     *
     * @param view view可以传多个，可以获取到各个item的值
     */
    private void createAlert(View... view) {
        final View viewDialog = View.inflate(CarBureauActivity.this, R.layout.dialog_bureau_item, null);
        Dialog dialog = new Dialog(CarBureauActivity.this);
        dialog.setTitle("自定义");
        TextView tvPlate = (TextView) viewDialog.findViewById(R.id.tvPlate);
        EditText etMoney = (EditText) viewDialog.findViewById(R.id.etMoney);
        Button btnRecharge = (Button) viewDialog.findViewById(R.id.btnRecharge);
        Button btnCancel = (Button) viewDialog.findViewById(R.id.btnCancel);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < view.length; i++) {
            if (view[i] == null) {
                continue;
            }
            TextView licensePlate = (TextView) view[i].findViewById(R.id.licensePlate);
            String tlp = licensePlate.getText().toString();
            sb.append(tlp);
            sb.append("  ");
        }


        tvPlate.setText(sb.toString());
        dialog.setContentView(viewDialog);
        dialog.show();
        btnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View t) {

                String tm = ((EditText) viewDialog.findViewById(R.id.etMoney)).getText().toString().trim();  //这里题目只能充值为一样的
                Integer money=Integer.parseInt(tm);
                for (int i = 0; i < view.length; i++) {
                    if(view[i]==null){continue;}
                    TextView licensePlate = (TextView) view[i].findViewById(R.id.licensePlate);
                    String tlp = licensePlate.getText().toString();
                    String tbc = ((TextView) view[i].findViewById(R.id.money)).getText().toString().trim();
                    Integer tBalance=Integer.parseInt(tbc);
                    String name = ((TextView) view[i].findViewById(R.id.name)).getText().toString();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = format.format(System.currentTimeMillis());
                    int alertBalance = money+tBalance;
                    ContentValues values = new ContentValues();
                    values.put("licensePlate", tlp);  //车牌号
                    values.put("money", money);         //充值金额
                    values.put("alertBalance", alertBalance);   //充值后的总钱数目
                    values.put("name", name);           //姓名
                    values.put("time", time);           //充值时间
                    db.insert("carBureauAccount", null, values);
                    TextView lastMoney=(TextView) view[i].findViewById(R.id.money);
                    lastMoney.setText(String.valueOf(alertBalance));
                }
                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //如果显示 多个的话使用Map存储起来
    private Map<String, View> selectViewData = new HashMap<>();

}
