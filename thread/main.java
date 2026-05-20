package com.example.threads2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button start;
    private Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
    }
    public void start(View view)
    {
            //ExampleThread thread = new ExampleThread(10);
            //thread.start();
        ExampleRunnable runnable = new ExampleRunnable(10);
        new Thread(runnable).start();
    }
    public void stop(View view){

    }
    class ExampleThread extends Thread{
        int sec;
        ExampleThread(int sec){
            this.sec = sec;
        }

        @Override
        public void run() {
            for(int i=0 ; i< 10 ; i++){
                Log.d(TAG,"started Thread"+i);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    class ExampleRunnable implements Runnable{
        int sec;
        ExampleRunnable(int sec){
            this.sec = sec;
        }

        @Override
        public void run() {
            for(int i=0 ; i< 10 ; i++){
                if(i == 5){
                    mainHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            start.setText("50%");
                        }
                    });
                }
                Log.d(TAG,"startThread: "+i);
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

