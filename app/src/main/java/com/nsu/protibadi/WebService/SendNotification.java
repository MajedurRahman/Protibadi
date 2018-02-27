package com.nsu.protibadi.WebService;

import android.util.Log;

import com.nsu.protibadi.WebService.Request.SimpleNotification;
import com.nsu.protibadi.WebService.Response.SimpleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Majedur Rahman on 2/28/2018.
 */

public class SendNotification implements Callback<SimpleResponse> {

    NotificationServiceInterface notificationServiceInterface;

    public SendNotification() {

    }

    public void sendNotificationRequest(SimpleNotification req) {
        notificationServiceInterface = WebServiceClient.getServiceClient().create(NotificationServiceInterface.class);
        Call<SimpleResponse> simpleResponseCall = notificationServiceInterface.sendNotification(req);
        simpleResponseCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
        try {
            Log.e("Response", "onResponse: ." + response.body().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<SimpleResponse> call, Throwable t) {

    }
}
