package com.nsu.protibadi.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Majedur Rahman on 2/9/2018.
 */

public class LocationService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Timer timer = new Timer("sdd");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerLog();
            }
        },2000,2000);
        return START_STICKY;
    }

    void timerLog(){

        Log.e("Start", " Timer");
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
