package com.nsu.protibadi.Service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.media.AsyncPlayer;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.nsu.protibadi.Activity.HomeActivity;
import com.nsu.protibadi.Activity.TrackingActivity;
import com.nsu.protibadi.Activity.UserDetailsActivity;
import com.nsu.protibadi.Model.CustomLatLng;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.palaima.smoothbluetooth.Device;
import io.palaima.smoothbluetooth.SmoothBluetooth;

import static com.nsu.protibadi.Utils.Constant.getSharedPref;
import static com.nsu.protibadi.Utils.Constant.getTimer;
import static com.nsu.protibadi.Utils.Constant.mSmoothBluetooth;

/**
 * Created by Majedur Rahman on 2/9/2018.
 */

public class BluetoothService extends Service {

    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();


    public static FusedLocationProviderClient fusedLocationProviderClient;
    Location location;
    static Context context;
    MediaPlayer mediaPlayer;
    public static boolean alreadyEmergency;
    private String userId;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = getApplicationContext();
        initComponent();
        return START_STICKY;
    }

    void initComponent() {
        userId = fUser.getUid();
        getLastLocation();

        alreadyEmergency = false;
        if (mSmoothBluetooth == null) {
            mSmoothBluetooth = new SmoothBluetooth(context);
        }
        mSmoothBluetooth.setListener(mListener);

        if (!mSmoothBluetooth.isConnected()) {
            mSmoothBluetooth.doDiscovery();
            mSmoothBluetooth.tryConnection();
        }
    }

    protected void getLastLocation() {

        if (fusedLocationProviderClient == null)
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                if (getSharedPref(context).getInt(Constant.IS_TRACKING_RUNNING, 0) == Constant.TRACKING_RUNNING) {
                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {

                            location = task.getResult();
                            if (location != null) {
                                Log.e("Last Location", location.getLatitude() + " " + location.getLongitude());
                                Constant.USER_REF.child(userId).child(Constant.FOOT_PRINT_TAG)
                                        .child(getSharedPref(context).getLong(Constant.CURRENT_TRACKING_NUMBER, 00000000000000) + "")
                                        .push().setValue(new CustomLatLng(System.currentTimeMillis(), location.getLatitude(), location.getLongitude())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Log.e("Cloud Confirmation ", "Successfully posted in Cloud ");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Cloud Confirmation ", "Failed to Store posted in Cloud " + e.getMessage());

                                    }
                                });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Last Location", "Last Location Failed " + e.getMessage());

                        }
                    });


                }
            }
        };
        getTimer().scheduleAtFixedRate(timerTask, 5000, 5000);


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private SmoothBluetooth.Listener mListener = new SmoothBluetooth.Listener() {
        @Override
        public void onBluetoothNotSupported() {

        }

        @Override
        public void onBluetoothNotEnabled() {

        }

        @Override
        public void onConnecting(Device device) {

        }

        @Override
        public void onConnected(Device device) {

        }

        @Override
        public void onDisconnected() {

        }

        @Override
        public void onConnectionFailed(Device device) {

        }

        @Override
        public void onDiscoveryStarted() {

        }

        @Override
        public void onDiscoveryFinished() {

        }

        @Override
        public void onNoDevicesFound() {
        }

        @Override
        public void onDevicesFound(final List<Device> deviceList,
                                   final SmoothBluetooth.ConnectionCallback connectionCallback) {
            try {
                if (!deviceList.isEmpty()) {

                    for (Device device : deviceList) {

                        if (device.getName().equalsIgnoreCase(Constant.DEVICE_NAME)) {
                            if (!mSmoothBluetooth.isConnected())
                                connectionCallback.connectTo(device);

                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

        @Override
        public void onDataReceived(int data) {
            Log.e("Emergency Data  :", (char) data + " " + Thread.currentThread().getName());

            if (String.valueOf((char) data).equalsIgnoreCase("E") && isTrackingRunning() == 0) {
                int trackingFLAG = getSharedPref(context).getInt(Constant.IS_TRACKING_RUNNING, 0);

                if (trackingFLAG == Constant.TRACKING_END) {
                    Toast.makeText(context, "Tracking Started", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPref(context).edit();
                    editor.putInt(Constant.IS_TRACKING_RUNNING, Constant.TRACKING_RUNNING);
                    editor.putLong(Constant.CURRENT_TRACKING_NUMBER, System.currentTimeMillis());
                    editor.commit();

                    Log.e("Tracking :", "New Tracking Started " + getSharedPref(context).getLong(Constant.CURRENT_TRACKING_NUMBER, 00000000000000));

                }


            }

        }
    };


    public static int isTrackingRunning() {
        int trackingFLAG = getSharedPref(context).getInt(Constant.IS_TRACKING_RUNNING, 0);
        return trackingFLAG;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }
}
