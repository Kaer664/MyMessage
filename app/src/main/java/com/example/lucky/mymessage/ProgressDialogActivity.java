package com.example.lucky.mymessage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProgressDialogActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3;
    private ProgressDialog dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        dlg = new ProgressDialog(ProgressDialogActivity.this);
        dlg.setIcon(R.drawable.img1);
        dlg.setMessage("这是一个进度条");
        dlg.setTitle("进度条");
        dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.show();
                new Thread() {
                    @Override
                    public void run() {
                        for (int i = 0; i <= 100; i++) {
                            Message msg = handler.obtainMessage();
                            msg.arg1 = i;
                            msg.what = MESSAGE_WORK;
                            handler.sendMessage(msg);
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(MESSAGE_FINISH);
                    }
                }.start();
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_item, null);
        final EditText etUser = (EditText) view.findViewById(R.id.etUser);
        final EditText etPassword = (EditText) view.findViewById(R.id.etPassword);
        builder.setTitle("自定义弹窗");
        builder.setView(view);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ProgressDialogActivity.this, "用户名：" +
                        etUser.getText().toString() + "\n密码：" + etPassword.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });





        final View viewDialog = View.inflate(this, R.layout.dialog_item2, null);
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("自定义");
        final EditText etName = (EditText) viewDialog.findViewById(R.id.etUser);
        final EditText etPassword1 = (EditText) viewDialog.findViewById(R.id.etPassword);
        final Button btnOk = (Button) viewDialog.findViewById(R.id.btnOk);
        dialog.setContentView(viewDialog);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProgressDialogActivity.this, "用户名：" +
                        etName.getText().toString() + "\n密码：" + etPassword1.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

    }

    private static final int MESSAGE_WORK = 0x001;
    private static final int MESSAGE_FINISH = 0x002;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_WORK:
                    dlg.setProgress(msg.arg1);
                    break;
                case MESSAGE_FINISH:
                    dlg.dismiss();
                    dlg.cancel();
                    break;
            }
        }
    };
}
