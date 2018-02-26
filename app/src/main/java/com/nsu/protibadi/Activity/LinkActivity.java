package com.nsu.protibadi.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nsu.protibadi.Model.LinkJoinModel;
import com.nsu.protibadi.Model.LinkModel;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

import java.util.Random;

import static com.nsu.protibadi.Utils.Constant.LINKED_WITH;

public class LinkActivity extends AppCompatActivity {
    public final String TAG = "Link Activity";
    public boolean isConfirm;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Dialog waitingDialog;
    TextView tokenTv;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        isConfirm = false;
        Random random = new Random();
        waitingDialog = new Dialog(LinkActivity.this);

        waitingDialog.setContentView(R.layout.waitting_for_connection_dialog);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
        token = String.valueOf(Math.abs(random.nextInt(959116155)));
        final LinkModel linkModel = new LinkModel(user.getUid(), user.getDisplayName());
        Constant.LINK_REF.child(token.trim()).push().setValue(linkModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                tokenTv = waitingDialog.findViewById(R.id.tv_Id);
                tokenTv.setText(token);

            }
        });


        Constant.LINK_REF.child(token).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {

                    if (!isConfirm) {
                        final LinkModel linkModel1 = dataSnapshot.getValue(LinkModel.class);
                        if (linkModel1 != null) {
                            if (linkModel1.isConfermation()) {

                                LinkJoinModel linkJoinModel = new LinkJoinModel();
                                linkJoinModel.setJoin_with(linkModel1.getJoinerId());
                                linkJoinModel.setJoinerName(linkModel1.getJoinWithName());
                                linkJoinModel.setLink_with(user.getUid());
                                linkJoinModel.setLinkerName(user.getDisplayName());
                                linkJoinModel.setTimeData(System.currentTimeMillis());
                                Constant.USER_REF.child(user.getUid()).child(LINKED_WITH).push().setValue(linkJoinModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(LinkActivity.this, "Successfully Linked with " + linkModel1.getLinkWithName(), Toast.LENGTH_SHORT).show();
                                        finish();
                                        waitingDialog.cancel();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LinkActivity.this, "Linked Failed - Try Again..", Toast.LENGTH_SHORT).show();
                                        finish();
                                        waitingDialog.cancel();
                                    }
                                });


                            }
                        }
                    }

                    isConfirm = false;
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        waitingDialog.findViewById(R.id.waitting_cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isConfirm = true;
                Constant.LINK_REF.child(token).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        finish();
                        waitingDialog.cancel();

                    }
                });
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (token != null)
            Constant.LINK_REF.child(token).removeValue();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
