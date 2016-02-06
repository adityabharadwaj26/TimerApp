package com.example.aditya.timerapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.EventListener;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button pauseButton;
    private Button resetButton;
    private ProgressBar progress;
    private TextView timerValue;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int progressStatus = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerValue = (TextView) findViewById(R.id.timerVal);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
            }
        });
        pauseButton = (Button) findViewById(R.id.stopButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                timeSwapBuff = 0;
                timerValue.setText("00:00:000");
                progress.setProgress(0);
                customHandler.removeCallbacks(updateTimerThread);
            }
        });
        progress = (ProgressBar) findViewById(R.id.progressBar);


    }
    private Runnable updateTimerThread = new Runnable() {
        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            int hour = mins / 60;
            int pomodoro = 25;
            secs = secs % 60;
            if (mins == pomodoro){
                timerValue.setTextColor(Color.RED);

            }
            int milliseconds = (int) (updatedTime % 1000);
            progressStatus = (int) mins;
            int stat = progressStatus / pomodoro;

            timerValue.setText(String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":"
                            + String.format("%02d", secs) + ":"
                            + String.format("%03d", milliseconds));

            progress.setProgress((progressStatus / pomodoro) * 100);
            customHandler.postDelayed(this, 0);
        }
    };
}
