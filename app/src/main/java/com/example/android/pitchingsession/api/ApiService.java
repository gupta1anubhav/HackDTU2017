package com.example.android.pitchingsession.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sushila on 10/7/2017.
 */

public class ApiService {
    public static API api= null;
    public static API getApi(){
        if (api==null){
            Retrofit retrofit= new Retrofit.Builder().baseUrl("https://gist.githubusercontent.com")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            api= retrofit.create(API.class);
        }
        return api;
    }
}