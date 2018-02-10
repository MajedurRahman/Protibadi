package com.nsu.protibadi.Utils;

import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Majedur Rahman on 2/9/2018.
 */

public class Constant {

    public static final String IS_TRACKING_RUNNING = "isTrackingRunning" ;
    public static final int TRACKING_RUNNING = 1;
    public static final int TRACKING_END = 0;
    public static final String TRACKING = "Tracking";
    public static final String PREF = "navPref";

    public static final FirebaseDatabase database =FirebaseDatabase.getInstance() ;
    public static final DatabaseReference USER_REF = database.getReference("Proibadi").child("User");


    public static final int EMERGENCY_NUMBER = 999;

}
