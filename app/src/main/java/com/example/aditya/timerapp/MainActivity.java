package com.example.aditya.timerapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

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
    private boolean stopped = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        final CountDown timer = new CountDown();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerValue = (TextView) findViewById(R.id.timerVal);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            timer.start();
            }
        });
        pauseButton = (Button) findViewById(R.id.stopButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    timer.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                timer.cancel();
            }
        });
        progress = (ProgressBar) findViewById(R.id.progressBar);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void stopRun() {
        //if (updatedTime == 0) {
            //Toast.makeText(new MainActivity(), "StopRun",Toast.LENGTH_LONG).show();
        timeSwapBuff = 0;
        timerValue.setText("00:00:000");
        //updateTimerThread.
        customHandler.removeCallbacks(updateTimerThread);
        //}
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            //long totalMilliseconds = 1500000;
            //while (!stopped){
                long totalMilliseconds = 15000;
                updatedTime = totalMilliseconds - SystemClock.currentThreadTimeMillis();
                int secs = (int) (updatedTime / 1000);
                int mins = secs / 60;
                int hour = mins / 60;
                secs = secs % 60;
                int milliseconds = (int) (updatedTime % 1000);
                progressStatus = (int) (totalMilliseconds - updatedTime);

                //while(stopped == false) {
                    timerValue.setText(String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":"
                            + String.format("%02d", secs) + ":"
                            + String.format("%03d", milliseconds));
                //}

                progress.setMax((int) totalMilliseconds);
                progress.setProgress(progressStatus);
                customHandler.postDelayed(this, 0);
                if (updatedTime == 0) {
                    //Toast.makeText(new MainActivity(), "Void Stop call",Toast.LENGTH_LONG).show();
                    //stopRun();
                    stopped = true;
                } else
                    stopped = false;
            }
        //}
    };

    public void stop() {
        //Toast toast = new Toast()
        Toast.makeText(new MainActivity(), "Void Stop",Toast.LENGTH_LONG).show();
        customHandler.removeCallbacks(updateTimerThread);
    }

    /*@Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.aditya.timerapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.aditya.timerapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/

    /*private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            int hour = mins / 60;
            int pomodoro = 2;
            secs = secs % 60;
            if (mins == pomodoro){
                timerValue.setTextColor(Color.BLUE);

            }
            int milliseconds = (int) (updatedTime % 1000);
            progressStatus = (int) secs;
            int stat = progressStatus / 60;

            timerValue.setText(String.format("%02d", hour) + ":" + String.format("%02d", mins) + ":"
                            + String.format("%02d", secs) + ":"
                            + String.format("%03d", milliseconds));

            progress.setMax(pomodoro);
            progress.setProgress((stat / pomodoro)* 100);
            customHandler.postDelayed(this, 0);
        }
    };*/
}
