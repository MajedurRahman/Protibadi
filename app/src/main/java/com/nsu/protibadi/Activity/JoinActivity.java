package com.nsu.protibadi.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.nsu.protibadi.Model.LinkModel;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

public class JoinActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    ProgressBar submitProgressBar;
    EditText editText;
    String uid;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.join_dailog_layout);

        //dialog.setCancelable(false);
        dialog.show();
        editText = dialog.findViewById(R.id.user_link_id_et);
        submitProgressBar = dialog.findViewById(R.id.submit_progress);
        dialog.findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uid = editText.getText().toString();
                submitProgressBar.setVisibility(View.VISIBLE);
                editText.setVisibility(View.GONE);

                if (!editText.getText().toString().trim().isEmpty())
                    Constant.LINK_REF.child(uid).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            try {
                                LinkModel model = dataSnapshot.getValue(LinkModel.class);

                                if (model != null) {
                                    model.setConfermation(true);
                                    model.setJoinWithName(user.getDisplayName());
                                    model.setJoinerId(user.getUid());
                                    Constant.LINK_REF.child(uid).setValue(model);
                                    //TODO Coome here to do some thing
                                    Toast.makeText(JoinActivity.this, "Successfully added with" + model.getJoinWithName(), Toast.LENGTH_SHORT).show();
                                    finish();
                                    dialog.cancel();

                                }
                            } catch (Exception ex) {
                                Log.e("Error", "onChildAdded: " + ex.getMessage());
                            }

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

            }
        });

        dialog.findViewById(R.id.cancel_submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                finish();
            }
        });
    }
}
