package com.nsu.protibadi.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        initActions();
    }

    void initActions() {

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserDetailsActivity.this, AuthActivity.class));
                finish();
            }
        });
    }
}
