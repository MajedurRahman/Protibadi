package com.nsu.protibadi.NotificationService;

import android.app.Notification;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nsu.protibadi.Activity.HomeActivity;
import com.nsu.protibadi.R;

import java.util.Map;

import br.com.goncalves.pugnotification.notification.PugNotification;

/**
 * Created by Majedur Rahman on 2/27/2018.
 */

public class ProtibadiMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();

        Log.i("onMessageReceived", "onMessageReceived: " + data.get("Title").toString());
        PugNotification.with(getApplicationContext())
                .load()
                .title("Title")
                .message("Message")
                .smallIcon(R.mipmap.ic_launcher_round)
                .largeIcon(R.drawable.ic_group_add)
                .flags(Notification.DEFAULT_ALL)
                .click(HomeActivity.class, null)
                .color(R.color.colorPrimary)
                .autoCancel(false)
                .simple()
                .build();

    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
