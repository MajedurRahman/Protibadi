package com.nsu.protibadi.Activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.os.Build;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.nsu.protibadi.BuildConfig;

import com.nsu.protibadi.Service.LocationService;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    // Write a message to the database
    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference currentPositionREF;
    private DatabaseReference footPrintREF;
    ArrayList<String> TrackPointKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(Constant.IS_TRACKING_RUNNING, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_home);
        initActions();
        initDBRef();

        showDebugDBAddressLogToast(this);
    }

    private void initDBRef() {

        Log.e("Firebase user ", fUser.getUid());
        footPrintREF = Constant.USER_REF.child(fUser.getUid()).child("FootPrint");
        currentPositionREF = Constant.USER_REF.child(fUser.getUid()).child("currentPosition");
        DUMMY_DATA();
    }

    void initActions() {
        findViewById(R.id.current_position).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CurrentPositionActivity.class));
                // startService(new Intent(HomeActivity.this, LocationService.class));
            }
        });

        findViewById(R.id.record_foot_print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        findViewById(R.id.call_999).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkPermission();
            }
        });

        findViewById(R.id.foot_print_tracking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TrackPointKey.isEmpty()) {
                    startActivity(new Intent(HomeActivity.this, FootPrintHistoryActivity.class).putExtra("FootPrintKey", TrackPointKey));
                } else {
                    Toast.makeText(HomeActivity.this, "No Foot Print Found !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.user_details).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, UserDetailsActivity.class));
            }
        });
    }

    void DUMMY_DATA() {
        TrackPointKey = new ArrayList<>();

        footPrintREF.keepSynced(true);
        footPrintREF.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                TrackPointKey.add(dataSnapshot.getKey());
                Log.e("Data", dataSnapshot.getKey());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                TrackPointKey.remove(dataSnapshot.getKey());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // startActivity(new Intent(HomeActivity.this,FootPrintHistoryActivity.class));
      /*  currentPositionREF.setValue(new LatLng(23.4554,90.545454));
        List<CustomLatLng> TrackPoint = new ArrayList<>();
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));
        TrackPoint.add(new CustomLatLng(System.currentTimeMillis() , 23.566556,89.46566));

        footPrintREF.child(String.valueOf(System.currentTimeMillis())).setValue(TrackPoint).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(HomeActivity.this, "Data upload Complete !!", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeActivity.this, " Failed to Save Data!" + " " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    void showAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are want to Start Tracking of Your Position ");
        alertDialogBuilder.setPositiveButton("Accept",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        int trackingFLAG = sharedpreferences.getInt(Constant.IS_TRACKING_RUNNING, 0);

                        if (trackingFLAG == Constant.TRACKING_END) {
                            startActivity(new Intent(HomeActivity.this, TrackingActivity.class));
                            Toast.makeText(HomeActivity.this, "Tracking Started", Toast.LENGTH_LONG).show();
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt(Constant.IS_TRACKING_RUNNING, Constant.TRACKING_RUNNING);
                            editor.commit();
                            finish();
                        } else if (trackingFLAG == Constant.TRACKING_RUNNING) {
                            Toast.makeText(HomeActivity.this, "Tracking Running Already", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(HomeActivity.this, TrackingActivity.class));
                        }
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public static void showDebugDBAddressLogToast(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
            } catch (Exception ignore) {

            }
        }
    }

    private void checkPermission() {

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constant.EMERGENCY_NUMBER));
                startActivity(intent);
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(HomeActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(HomeActivity.this)
                .setPermissionListener(permissionlistener)
                .setDeniedTitle("")
                .setDeniedMessage("")
                .setGotoSettingButtonText("go to Settings")
                .setPermissions(android.Manifest.permission.CALL_PHONE)
                .check();

    }
}
