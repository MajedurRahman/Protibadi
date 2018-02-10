package com.nsu.protibadi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nsu.protibadi.R;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

public class UserDetailsActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        CircularImageView circularImageView = findViewById(R.id.profile_picture);

        Picasso.with(this).load(user.getPhotoUrl()).into(circularImageView);

    }
}
