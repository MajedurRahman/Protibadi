package com.nsu.protibadi.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nsu.protibadi.R;

import com.nsu.protibadi.Utils.Constant;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import static com.nsu.protibadi.Utils.Constant.getSharedPref;

public class UserDetailsActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Button trackingButton;

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

                SharedPreferences.Editor editor = getSharedPref(UserDetailsActivity.this).edit();
                editor.putInt(Constant.IS_TRACKING_RUNNING, Constant.TRACKING_END);
                editor.commit();
                trackingButton.setVisibility(View.GONE);
                //getTimer().cancel();
                // getTimer().purge();
                Log.e("Istracking ", getSharedPref(UserDetailsActivity.this).getInt(Constant.IS_TRACKING_RUNNING, 0) + "");
            }
        });
    }

}
