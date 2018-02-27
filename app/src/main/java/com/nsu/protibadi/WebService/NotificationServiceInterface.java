package com.nsu.protibadi.WebService;

import com.nsu.protibadi.WebService.Request.SimpleNotification;
import com.nsu.protibadi.WebService.Response.SimpleResponse;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Majedur Rahman on 2/14/2018.
 */

public interface NotificationServiceInterface {
    String AUTH = "Authorization: key=AAAAATULgAA:APA91bG24QqEodcqkyS50cbJkyP21BE24Z1r7DFsNPv9eHKIEIvcNwGaewiQ1h8NAOyL6_kgL5F1PHDCn7IT9frHLXBTsJJbjX5wwp3jo4W_n-cbGn3M6RJP6LxoJjRYlY5PojpfL3dq";
    String CONTENT_TYPE = "Content-Type: application/json";

    @Headers({AUTH, CONTENT_TYPE})
    @POST("send")
    retrofit2.Call<SimpleResponse> sendNotification(@Body SimpleNotification requestBody);
}

