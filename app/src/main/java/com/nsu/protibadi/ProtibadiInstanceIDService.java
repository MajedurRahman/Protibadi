package com.nsu.protibadi;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.nsu.protibadi.Utils.Constant;


/**
 * Created by Majedur Rahman on 2/27/2018.
 */

public class ProtibadiInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.e("FirebaseToken", "onTokenRefresh: " + token);

        Constant.getSharedPref(getApplicationContext()).edit().putString(Constant.FCM_TOKEN, token).commit();
        Log.e("From Shared pref", "onTokenRefresh: " + Constant.getSharedPref(getApplicationContext()).getString(Constant.FCM_TOKEN, ""));

    }

}
