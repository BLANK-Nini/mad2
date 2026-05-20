package com.example.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel nc = new NotificationChannel("01","Notication", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(nc);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder ncb = new NotificationCompat.Builder(getApplicationContext(),"01");
                ncb.setSmallIcon(R.drawable.ic_baseline_auto_awesome_24);
                ncb.setContentTitle("Notify💕💕💕");
                ncb.setContentText("Hey Successfully send notification");
                NotificationManagerCompat nmc =NotificationManagerCompat.from(MainActivity.this);
                nmc.notify(01,ncb.build());
            }
        });

    }
}