package com.nsu.protibadi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nsu.protibadi.Model.ECNumber;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

import java.util.List;

import static com.nsu.protibadi.Utils.Constant.EMERGENCY_CONACT_NUMBER;

/**
 * Created by Majedur Rahman on 2/22/2018.
 */

public class ECNAdapter extends RecyclerView.Adapter<ECNAdapter.ECNHolder> {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    Context context;
    List<ECNumber> numberList;
    LayoutInflater layoutInflater;

    public ECNAdapter(Context c, List<ECNumber> ecnNumberList) {

        context = c;
        numberList = ecnNumberList;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ECNHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = layoutInflater.inflate(R.layout.ecnumber_list_item, parent, false);
        return new ECNHolder(v);
    }

    @Override
    public void onBindViewHolder(ECNHolder holder, int position) {

        holder.nameTV.setText(numberList.get(position).getName());
        holder.numberTV.setText(numberList.get(position).getPhoneNumber());
        holder.onClick(position);

    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }

    class ECNHolder extends RecyclerView.ViewHolder {

        String childKey;

        TextView nameTV, numberTV;
        Button deleteButton;

        public ECNHolder(View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.nameTextView);
            numberTV = itemView.findViewById(R.id.numberTextView);
            deleteButton = itemView.findViewById(R.id.deleteNumber);
        }

        void onClick(final int position) {
            childKey = "";

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.USER_REF.child(user.getUid()).child(EMERGENCY_CONACT_NUMBER).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            try {
                                for (DataSnapshot ecNumber : dataSnapshot.getChildren()) {
                                    ECNumber number = ecNumber.getValue(ECNumber.class);
                                    if (number.getPhoneNumber().equals(numberList.get(position).getPhoneNumber()) && number.getName().equals(numberList.get(position).getName())) {

                                        childKey = ecNumber.getKey();
                                        Log.e("Child key ", childKey);
                                        Constant.USER_REF.child(user.getUid()).child(EMERGENCY_CONACT_NUMBER).child(childKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                notifyItemRemoved(position);
                                            }
                                        });
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            });
        }
    }
}
