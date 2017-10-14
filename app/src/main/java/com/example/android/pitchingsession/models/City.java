package com.example.android.pitchingsession.models;

/**
 * Created by Sushila on 10/7/2017.
 */

public class City {
    String latitude;
    String longitude;
    String CityName;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getCityName() {
        return CityName;
    }

    public City(String latitude, String longitude, String cityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        CityName = cityName;
    }
}
