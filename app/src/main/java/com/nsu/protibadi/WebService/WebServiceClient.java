package com.nsu.protibadi.WebService;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Majedur Rahman on 2/14/2018.
 */

public class WebServiceClient {
    public static final String BASE_URL = "https://fcm.googleapis.com/fcm/";
    public static Retrofit retrofit = null;

    public static Retrofit getServiceClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
