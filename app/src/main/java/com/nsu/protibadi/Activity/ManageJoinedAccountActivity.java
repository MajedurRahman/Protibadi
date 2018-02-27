package com.nsu.protibadi.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.nsu.protibadi.Adapter.JoinedAccountAdapter;
import com.nsu.protibadi.Model.LinkedWith;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.nsu.protibadi.Utils.Constant.JOINED_WITH;

public class ManageJoinedAccountActivity extends AppCompatActivity {
    private static final String TAG = ManageLinkedAccountActivity.class.getSimpleName();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    List<LinkedWith> linkJoinModels;
    RecyclerView recyclerView;
    private JoinedAccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_linked_account);

        linkJoinModels = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view_manage_linked_account);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new JoinedAccountAdapter(this, linkJoinModels);
        recyclerView.setAdapter(adapter);


        manegeAccountList();
    }

    private void manegeAccountList() {

        Constant.USER_REF.child(user.getUid()).child(JOINED_WITH).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    linkJoinModels.add(dataSnapshot.getValue(LinkedWith.class));
                    adapter.notifyDataSetChanged();

                    Log.e(TAG, "onChildAdded: " + s);
                } catch (Exception ex) {
                    Log.e(TAG, "onChildAdded: ", ex);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                try {
                    for (LinkedWith linkedWith : linkJoinModels) {
                        if (linkedWith.getId().equals(dataSnapshot.getValue(LinkedWith.class).getId())) {
                            linkJoinModels.remove(linkedWith);
                            adapter.notifyDataSetChanged();
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
