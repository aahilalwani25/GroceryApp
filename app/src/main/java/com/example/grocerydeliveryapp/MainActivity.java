package com.example.grocerydeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar pgb=(ProgressBar) findViewById(R.id.pgb);

        Timer timer=new Timer();

        //delay the process for a while for 5000(5 seconds)
        timer.schedule( new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    pgb.incrementProgressBy(10);
                    Intent intent=new Intent(MainActivity.this,Main_Screen_Activity.class);
                    startActivity(intent);
                }
            }
        }, 5000);


    }
}