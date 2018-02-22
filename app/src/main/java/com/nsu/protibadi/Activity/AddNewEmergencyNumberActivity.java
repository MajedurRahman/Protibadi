package com.nsu.protibadi.Activity;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.nsu.protibadi.Adapter.ECNAdapter;
import com.nsu.protibadi.Model.ECNumber;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.nsu.protibadi.Utils.Constant.EMERGENCY_CONACT_NUMBER;

public class AddNewEmergencyNumberActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DatabaseReference emergencyContactRef;
    List<ECNumber> ecNumbersList;
    ECNAdapter adapter;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_emergency_number);
        initComponent();
        emergencyContactRef = Constant.USER_REF.child(user.getUid()).child(EMERGENCY_CONACT_NUMBER);
        geExistingEmergencyNumber();
    }

    void initComponent() {
        ecNumbersList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.ecnRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ECNAdapter(this, ecNumbersList);
        recyclerView.setAdapter(adapter);
        initOnClickAction();

    }

    private void initOnClickAction() {
        findViewById(R.id.add_new_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(AddNewEmergencyNumberActivity.this);
                dialog.setContentView(R.layout.add_number_dialog);
                dialog.show();

                Button button = dialog.findViewById(R.id.add_number_btn);
                final EditText numberEt = dialog.findViewById(R.id.contact_number_et);

                final EditText nameEt = dialog.findViewById(R.id.name_Et);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (nameEt.getText().toString().isEmpty() && numberEt.getText().toString().isEmpty()) {
                            Toast.makeText(AddNewEmergencyNumberActivity.this, "One or more Field is Empty", Toast.LENGTH_SHORT).show();
                        } else {
                            ECNumber number = new ECNumber();
                            number.setName(nameEt.getText().toString().trim());
                            number.setPhoneNumber(numberEt.getText().toString().trim());

                            emergencyContactRef.push().setValue(number).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Log.e("ECN Number", "New Number is Added" + task.toString());

                                    dialog.cancel();
                                }
                            });
                        }

                    }
                });
            }
        });

    }

    public void geExistingEmergencyNumber() {

        emergencyContactRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                ecNumbersList.add(dataSnapshot.getValue(ECNumber.class));
                Log.e("ECNumber Added ", dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ECNumber ecNumber = dataSnapshot.getValue(ECNumber.class);

                for (ECNumber number : ecNumbersList) {
                    if (number.getName().equals(ecNumber.getName()) && number.getPhoneNumber().equals(ecNumber.getPhoneNumber())) {
                        ecNumbersList.remove(number);
                        Log.e("ECNumber Removed", ecNumber.getPhoneNumber().toString());
                        return;

                    }
                }
                adapter.notifyDataSetChanged();

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
