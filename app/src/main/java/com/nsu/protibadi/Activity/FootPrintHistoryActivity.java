package com.nsu.protibadi.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nsu.protibadi.Adapter.FootPrintHistoryAdapter;
import com.nsu.protibadi.R;

import java.util.ArrayList;
import java.util.List;

public class FootPrintHistoryActivity extends AppCompatActivity {
    FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    ArrayList<String> key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_print_history);
        key = getIntent().getStringArrayListExtra("FootPrintKey");
        RecyclerView recyclerView = findViewById(R.id.recyclerView_foot_print);

        recyclerView.setLayoutManager(new LinearLayoutManager(FootPrintHistoryActivity.this));
        FootPrintHistoryAdapter adapter = new FootPrintHistoryAdapter(FootPrintHistoryActivity.this,key , fUser.getUid());
        recyclerView.setAdapter(adapter);

    }

}
