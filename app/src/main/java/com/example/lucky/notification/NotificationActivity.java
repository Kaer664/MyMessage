package com.example.lucky.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.lucky.mymessage.R;

public class NotificationActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

    }

    public void createNotification(View view) {
        switch (view.getId()) {
            case R.id.btnNotification:
                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(this, DemoActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
                Notification nof = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.img1)
                        .setContentTitle("版本11-16以上使用")
                        .setContentText("这是一个内容")
                        .setTicker("新消息")
                        .setContentIntent(pi).getNotification();
                notificationManager.notify(1, nof);
                break;
        }
    }
}
