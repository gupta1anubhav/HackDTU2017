package com.example.android.pitchingsession.api;

import com.example.android.pitchingsession.models.CitiesPojo.JsonCity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sushila on 10/7/2017.
 */

public interface API {
    @GET("/dastagirkhan/00a6f6e32425e0944241/raw/33ca4e2b19695b2b93f490848314268ed5519894/gistfile1.json")
    Call<ArrayList<JsonCity>> getCities();

}
