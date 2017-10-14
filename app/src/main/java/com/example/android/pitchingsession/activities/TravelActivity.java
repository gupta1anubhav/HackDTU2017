package com.example.android.pitchingsession.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.pitchingsession.MainActivity;
import com.example.android.pitchingsession.activities.MapsActivity;
import com.example.android.pitchingsession.R;
import com.example.android.pitchingsession.models.MapsPojo.Location;
import com.example.android.pitchingsession.utils.Constants;

import static android.R.attr.src;

public class TravelActivity extends AppCompatActivity implements View.OnClickListener{
    String src,dest;
    String srckey,destkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                src= null;
                dest = null;
                srckey = null;
                destkey = null;
            } else {
                src= extras.getString("Source");
                dest = extras.getString("Destination");
                srckey = extras.getString(Constants.srclatlng);
                destkey = extras.getString(Constants.destlatlng);
            }
        } else {
            src= (String) savedInstanceState.getSerializable("Source");
            dest= (String) savedInstanceState.getSerializable("Destination");
            srckey = (String) savedInstanceState.getSerializable(Constants.srclatlng);
            destkey = (String) savedInstanceState.getSerializable(Constants.destlatlng);
        }
        findViewById(R.id.llcity).setOnClickListener(this);
        findViewById(R.id.lltrans).setOnClickListener(this);
        findViewById(R.id.llPlaces).setOnClickListener(this);
        findViewById(R.id.lllocate).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
            switch (view.getId()) {
                case R.id.llcity:
                    i = new Intent(TravelActivity.this, CityActivity.class);
                    startActivity(i);
                    break;

                case R.id.llPlaces:
                    i = new Intent(TravelActivity.this, PlacesActivity.class);
                    startActivity(i);
                    break;

                case R.id.lltrans:
                    i = new Intent(TravelActivity.this, TransportActivity.class);
                    i.putExtra(Constants.Source,src);
                    i.putExtra(Constants.Destination,dest);
                    i.putExtra(Constants.srclatlng,srckey);
                    i.putExtra(Constants.destlatlng,destkey);
                    startActivity(i);
                    break;

                case R.id.lllocate:
                    i = new Intent(TravelActivity.this, MapsActivity.class);
                    startActivity(i);
                    break;
            }
        }
    }

