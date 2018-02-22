package com.nsu.protibadi.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nsu.protibadi.Service.BluetoothService;

import java.sql.Time;
import java.util.Timer;

import io.palaima.smoothbluetooth.SmoothBluetooth;

/**
 * Created by Majedur Rahman on 2/9/2018.
 */

public class Constant {

    public static final String IS_TRACKING_RUNNING = "isTrackingRunning";
    public static final int TRACKING_RUNNING = 1;
    public static final int TRACKING_END = 0;
    public static final String TRACKING = "Tracking";
    public static final String PREF = "navPref";
    public static SharedPreferences sharedpreferences;

    public static final String PRITIBADI_PREF = "Protibadi";
    public static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static final DatabaseReference USER_REF = database.getReference("Proibadi").child("User");


    public static final int EMERGENCY_NUMBER = 999;
    public static String CURRENT_TRACKING_NUMBER = "CurrentTrackingNumber";
    public static String DEVICE_NAME = "HC-05";
    public static SmoothBluetooth mSmoothBluetooth;


    public static final String FOOT_PRINT_TAG = "FootPrint";
    public static final String EMERGENCY_CONACT_NUMBER = "EmergencyContacts";

    public static SharedPreferences getSharedPref(Context context) {
        if (sharedpreferences == null) {
            sharedpreferences = context.getSharedPreferences(Constant.PRITIBADI_PREF, Context.MODE_PRIVATE);

        }
        return sharedpreferences;
    }

    public static Timer timer;

    public static Timer getTimer() {
        if (timer == null)
            timer = new Timer("Loc");

        return timer;
    }


}
