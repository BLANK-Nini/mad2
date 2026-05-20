package com.example.broadcastreceive;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter("com.example.broadcastsend");
        MyBroadcastreciever obj = new MyBroadcastreciever();
        registerReceiver(obj,intentFilter);
    }
}