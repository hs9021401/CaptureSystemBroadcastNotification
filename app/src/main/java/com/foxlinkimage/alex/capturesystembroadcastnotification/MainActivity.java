package com.foxlinkimage.alex.capturesystembroadcastnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = (TextView)findViewById(R.id.msg);

        LocalBroadcastManager.getInstance(this).registerReceiver(onNotice, new IntentFilter("Msg"));
    }

    private BroadcastReceiver onNotice = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String ticker = intent.getStringExtra("ticker");
            String old_msg = msg.getText().toString();
            old_msg = old_msg + "\n" + ticker;
            msg.setText(old_msg);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }
}
