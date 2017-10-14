package com.example.android.pitchingsession.models.CitiesPojo;

/**
 * Created by Sushila on 10/7/2017.
 */

public class JsonCity {
    String name;
    String state;
    String lat;
    String lon;

    public JsonCity(String name, String state, String lat, String lon) {
        this.name = name;
        this.state = state;
        this.lat = lat;
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
