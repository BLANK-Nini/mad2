package com.example.broadcastreceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastreciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Broadcastreciever","BroadcastReciever message is recieved");
        Toast.makeText(context,"Broadcast message is recieved",Toast.LENGTH_SHORT).show();
    }
}
