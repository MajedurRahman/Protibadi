package com.nsu.protibadi.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

public class TrackingActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        sharedpreferences = getSharedPreferences(Constant.IS_TRACKING_RUNNING, Context.MODE_PRIVATE);

        int track = isStaredTracking();
        if (isStaredTracking() == Constant.TRACKING_RUNNING) {
            Log.e("Istracking " , isStaredTracking()+"");
        }
    }

    public int isStaredTracking() {
        int trackingFLAG = sharedpreferences.getInt(Constant.IS_TRACKING_RUNNING, 0);

        return trackingFLAG;
    }
}
