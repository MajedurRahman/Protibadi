package com.nsu.protibadi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nsu.protibadi.Activity.CurrentPositionActivity;
import com.nsu.protibadi.Activity.FootPrintMapActivity;
import com.nsu.protibadi.Model.CustomLatLng;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Majedur Rahman on 2/10/2018.
 */

public class FootPrintHistoryAdapter extends RecyclerView.Adapter<FootPrintHistoryAdapter.ItemViewHolder> {


    Context context;
    ArrayList<String> keyList;
    LayoutInflater inflater;
    String userId;

    public FootPrintHistoryAdapter(Context context, ArrayList<String> List, String uid) {
        this.context = context;
        this.keyList = List;
        userId = uid;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.foot_print_item, parent, false);

        return new ItemViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Log.e("Data", keyList.get(position));
        holder.name.setText(keyList.get(position));

        try {

            long time = Long.parseLong(keyList.get(position));
            holder.time.setText(DateFormat.getInstance().format(time));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.onclick(keyList.get(position), userId);

    }

    @Override
    public int getItemCount() {
        return keyList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView time;
        CardView cardView;
        Context context;

        public ItemViewHolder(View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.nameFootPrint);
            time = itemView.findViewById(R.id.footPrintTime);
            cardView = itemView.findViewById(R.id.historyCard);
            this.context = context;
        }

        public void onclick(final String Key , final String userId) {

            final List<CustomLatLng> customLatLngs = new ArrayList<>();
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.USER_REF.child(userId).child("FootPrint").child(Key).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                           for (DataSnapshot data : dataSnapshot.getChildren()){
                               customLatLngs.add(data.getValue(CustomLatLng.class));
                           }
                            Log.e("DataList " , customLatLngs.size() + " ");

                            context.startActivity(new Intent(context , FootPrintMapActivity.class).putExtra("ListOfTrackPoint", (Serializable) customLatLngs));
                            customLatLngs.clear();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                  //  context.startActivity(new Intent(context, CurrentPositionActivity.class).putExtra("FootPrintKey", Key));
                }
            });
        }
    }
}
