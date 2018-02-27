package com.nsu.protibadi;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Majedur Rahman on 2/27/2018.
 */

public class ProtibadiMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("onMessageReceived", "onMessageReceived: ");
        //TODO  Need to init Notification
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
