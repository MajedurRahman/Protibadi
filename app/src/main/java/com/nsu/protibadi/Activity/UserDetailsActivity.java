package com.nsu.protibadi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import static android.content.ContentValues.TAG;
import static com.nsu.protibadi.Utils.Constant.EMERGENCY_STATUS;
import static com.nsu.protibadi.Utils.Constant.getSharedPref;

public class UserDetailsActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Button trackingButton;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_details);
        initComponent();
        initActions();
    }

    private void initComponent() {
        trackingButton = findViewById(R.id.tracking_stop);
        if (getSharedPref(UserDetailsActivity.this).getInt(Constant.IS_TRACKING_RUNNING, 0) == 1) {

            trackingButton.setVisibility(View.VISIBLE);
        } else {
            trackingButton.setVisibility(View.GONE);
        }

        CircularImageView circularImageView = findViewById(R.id.profile_picture);
        Picasso.with(this).load(user.getPhotoUrl()).into(circularImageView);
    }

    private void initActions() {

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserDetailsActivity.this, AuthActivity.class));
                finish();
            }
        });

        findViewById(R.id.emergency_contact_num_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UserDetailsActivity.this, AddNewEmergencyNumberActivity.class));

            }
        });

        trackingButton.findViewById(R.id.tracking_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constant.USER_REF.child(user.getUid()).child(EMERGENCY_STATUS).setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        SharedPreferences.Editor editor = getSharedPref(UserDetailsActivity.this).edit();
                        editor.putInt(Constant.IS_TRACKING_RUNNING, Constant.TRACKING_END);
                        editor.commit();
                        trackingButton.setVisibility(View.GONE);
                        //getTimer().cancel();
                        // getTimer().purge();
                        Log.e(TAG, "onComplete: " + "Emergency Status Turn Off successful");

                        Log.e("Istracking ", getSharedPref(UserDetailsActivity.this).getInt(Constant.IS_TRACKING_RUNNING, 0) + "");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: " + "Emergency Status Turn Off unsuccessful");
                    }
                });

            }
        });


        findViewById(R.id.account_link_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(UserDetailsActivity.this);

                dialog.setContentView(R.layout.join_link_dailog_layout);


                dialog.findViewById(R.id.join_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(UserDetailsActivity.this, JoinActivity.class));
                    }
                });

                dialog.findViewById(R.id.link_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(UserDetailsActivity.this, LinkActivity.class));
                    }
                });

                dialog.show();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dialog != null) {
            dialog.cancel();
        }
    }
}
