package com.example.aditya.timerapp;

import java.util.TimerTask;

/**
 * Created by Aditya on 12/27/2015.
 */
public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        int i = 0;
        int j = 0;
        i = i + 1;
        if (i > 60) {
            i = 0;
            j = j + 1;
        }
        if (j >= 5) {
            //timer.stop();
        }
        //System.out.println("Timer task started at:");

    }
}
