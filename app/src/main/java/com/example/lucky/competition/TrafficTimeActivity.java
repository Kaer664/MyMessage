package com.example.lucky.competition;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.lucky.mymessage.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Address;
import util.NetUtils;

public class TrafficTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView1;
    private Spinner spinnerRoad;
    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_time);
        listView1 = (ListView) findViewById(R.id.listView1);
        spinnerRoad = (Spinner) findViewById(R.id.spinnerRoad);
        btnCheck = (Button) findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x001:
                    List<String> data = (List<String>) msg.obj;
                    try {
                        List<Map<String, Integer>> trafficTime = new ArrayList<>();
                        trafficTime.add(parserTime(data.get(0), 1));
                        trafficTime.add(parserTime(data.get(1), 2));

                        trafficTime.add(parserTime(data.get(2), 3));
                        List<Map<String, Integer>> t = sortMethod(trafficTime, spinnerRoad.getSelectedItem().toString());
                        SimpleAdapter adapter = new SimpleAdapter(TrafficTimeActivity.this, t, R.layout.road_item, new String[]{"roadID", "RedTime", "GreenTime", "YellowTime"}, new int[]{R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4});
                        listView1.setAdapter(adapter);
                        spinnerRoad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                List<Map<String, Integer>> t = sortMethod(trafficTime, spinnerRoad.getSelectedItem().toString());
                                SimpleAdapter adapter = new SimpleAdapter(TrafficTimeActivity.this, t, R.layout.road_item, new String[]{"roadID", "RedTime", "GreenTime", "YellowTime"}, new int[]{R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4});
                                listView1.setAdapter(adapter);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    private Map<String, Integer> parserTime(String s, int roadID) throws Exception {
        JSONObject j1 = new JSONObject(s);
        JSONObject serverInfo = j1.getJSONObject("serverInfo");
        Map<String, Integer> map = new HashMap<>();
        map.put("RedTime", serverInfo.getInt("RedTime"));
        map.put("GreenTime", serverInfo.getInt("GreenTime"));
        map.put("YellowTime", serverInfo.getInt("YellowTime"));
        map.put("roadID", roadID);
        List<String> list = new ArrayList<>();
        return map;
    }

    private List<Map<String, Integer>> sortMethod(List<Map<String, Integer>> data, String rule) {
        switch (rule) {
            case "路口升序":
                sort(data, "roadID", 1);
                return data;
            case "路口降序":
                sort(data, "roadID", -1);
                return data;
            case "红灯升序":
                sort(data, "RedTime", 1);
                return data;
            case "红灯降序":
                sort(data, "RedTime", -1);
                return data;
            case "绿灯升序":
                sort(data, "GreenTime", 1);
                return data;
            case "绿灯降序":
                sort(data, "GreenTime", -1);
                return data;
            case "黄灯升序":
                sort(data, "YellowTime", 1);
                return data;
            case "黄灯降序":
                sort(data, "YellowTime", -1);
                return data;
        }
        return null;
    }

    /**
     * @param list 排序对象
     * @param key  根据什么排序
     * @param is   升序(1)还是降序(-1)
     */
    public static void sort(final List<Map<String, Integer>> list, final String key, final int is) {
        Collections.sort(list, new Comparator<Map<String, Integer>>() {
            public int compare(Map<String, Integer> o1, Map<String, Integer> o2) {
                Integer t1 = o1.get(key);
                Integer t2 = o2.get(key);
                int t = t1.compareTo(t2);
                return t * is;
            }
        });
    }

    /**
     * 按钮点击事件方法
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCheck:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            List<String> data = new ArrayList<String>();
                            data.add(NetUtils.post(Address.GetTrafficLightConfigAction, "POST", "TrafficLightId=1"));
                            data.add(NetUtils.post(Address.GetTrafficLightConfigAction, "POST", "TrafficLightId=2"));
                            data.add(NetUtils.post(Address.GetTrafficLightConfigAction, "POST", "TrafficLightId=3"));
                            Message msg = handler.obtainMessage(0x001);
                            msg.obj = data;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
        }
    }
}
