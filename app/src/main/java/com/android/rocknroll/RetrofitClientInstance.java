package com.android.rocknroll;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {


    private static String API_BASE_URL = "https://salty-bayou-21277.herokuapp.com/";
    private static Retrofit retrofit;
    private static Gson gson;

    public static Retrofit getRetrofitInstace() {
        if (retrofit == null) {
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}

