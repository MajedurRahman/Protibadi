package com.nsu.protibadi.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nsu.protibadi.Model.LinkedWith;
import com.nsu.protibadi.R;
import com.nsu.protibadi.Utils.Constant;
import com.pkmmte.view.CircularImageView;

import java.util.List;

import static com.nsu.protibadi.Utils.Constant.JOINED_WITH;

/**
 * Created by Majedur Rahman on 2/27/2018.
 */

public class JoinedAccountAdapter extends RecyclerView.Adapter<JoinedAccountAdapter.ViewHolder> {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    List<LinkedWith> linkJoinModelList;
    Context context;
    LayoutInflater layoutInflater;

    public JoinedAccountAdapter(Context context, List<LinkedWith> list) {
        this.context = context;
        linkJoinModelList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public JoinedAccountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.link_account_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JoinedAccountAdapter.ViewHolder holder, int position) {

        holder.name.setText(linkJoinModelList.get(position).getName());
        holder.info.setText(linkJoinModelList.get(position).getInfo());
        holder.onClicked(position);
    }

    @Override
    public int getItemCount() {
        return linkJoinModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, info;
        CircularImageView circularImageView;
        ImageView deleteImage;

        public ViewHolder(View itemView) {
            super(itemView);
            circularImageView = itemView.findViewById(R.id.profile_picture);
            name = itemView.findViewById(R.id.name_link_account);
            info = itemView.findViewById(R.id.contact_info);
            deleteImage = itemView.findViewById(R.id.delete_linked_account_button);
        }

        public void onClicked(final int position) {
            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constant.USER_REF.child(user.getUid()).child(JOINED_WITH).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {
                                LinkedWith linkJoin = data.getValue(LinkedWith.class);
                                if (linkJoin.getId().equals(linkJoinModelList.get(position).getId())) {
                                    Constant.USER_REF.child(user.getUid()).child(JOINED_WITH).child(data.getKey()).removeValue();
                                    notifyItemRemoved(position);
                                }

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
