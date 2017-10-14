package com.example.android.pitchingsession.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.pitchingsession.R;
import com.squareup.picasso.Picasso;

import static java.lang.System.load;

public class CityInfo extends AppCompatActivity implements View.OnClickListener {
    ImageView iv;
    TextView tvName;
    String name = "";
    String lat = "";
    String lon = "";
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_city_info);
        iv = (ImageView) findViewById(R.id.iv);
        tvName = (TextView) findViewById(R.id.tvName);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name= null;
                lat = null;
                lon = null;
            } else {
                name= extras.getString("CityName");
                lat = extras.getString("Latitude");
                lon = extras.getString("Longitude");
                url = extras.getString("url");
            }
        } else {
            name= (String) savedInstanceState.getSerializable("CityName");
            lat= (String) savedInstanceState.getSerializable("Latitude");
            lon = (String) savedInstanceState.getSerializable("Longitude");
            url = (String) savedInstanceState.getSerializable("url");
        }
        Picasso.with(this).load(url).into(iv);
        tvName.setText(name);
        findViewById(R.id.llweather).setOnClickListener(this);
        findViewById(R.id.restau).setOnClickListener(this);
        findViewById(R.id.shoppp).setOnClickListener(this);
        findViewById(R.id.hangout).setOnClickListener(this);
        findViewById(R.id.musu).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
         switch (view.getId()){
             case R.id.llweather:
                 i = new Intent(CityInfo.this,WeatherActivity.class);
                 i.putExtra("CityName",name);
                 i.putExtra("Latitude",lat);
                 i.putExtra("Longitude",lon);
                 i.putExtra("url",url);
                 startActivity(i);
                 break;
             case R.id.musu:
                 i = new Intent(CityInfo.this, NearbyPlaces.class);
                 i.putExtra("CityName",name);
                 i.putExtra("Latitude",lat);
                 i.putExtra("Longitude",lon);
                 i.putExtra("Type","museum");
                startActivity(i);
                 break;
             case R.id.restau:
                 i = new Intent(CityInfo.this, NearbyPlaces.class);
                 i.putExtra("CityName",name);
                 i.putExtra("Latitude",lat);
                 i.putExtra("Longitude",lon);
                 i.putExtra("Type","restaurant");
                 startActivity(i);
                 break;
             case R.id.hangout:
                 i = new Intent(CityInfo.this, NearbyPlaces.class);
                 i.putExtra("CityName",name);
                 i.putExtra("Latitude",lat);
                 i.putExtra("Longitude",lon);
                 i.putExtra("Type","cafe");
                 startActivity(i);
                 break;
             case R.id.shoppp:
                 i = new Intent(CityInfo.this, NearbyPlaces.class);
                 i.putExtra("CityName",name);
                 i.putExtra("Latitude",lat);
                 i.putExtra("Longitude",lon);
                 i.putExtra("Type","shopping_mall");
                 startActivity(i);
                 break;
         }
    }
}
