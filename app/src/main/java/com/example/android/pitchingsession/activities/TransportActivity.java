package com.example.android.pitchingsession.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.android.pitchingsession.R;
import com.example.android.pitchingsession.utils.Constants;

import static com.example.android.pitchingsession.R.drawable.car;

public class TransportActivity extends AppCompatActivity {
LinearLayout rws;
    String src,dest,srckey,destkey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
         rws = (LinearLayout) findViewById(R.id.rws);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                src= null;
                dest = null;
                srckey = null;
                destkey = null;
            } else {
                src= extras.getString(Constants.Source);
                dest = extras.getString(Constants.Destination);
                srckey = extras.getString(Constants.srclatlng);
                destkey = extras.getString(Constants.destlatlng);
            }
        } else {
            src= (String) savedInstanceState.getSerializable("Source");
            dest= (String) savedInstanceState.getSerializable("Destination");
            srckey = (String) savedInstanceState.getSerializable(Constants.srclatlng);
            destkey = (String) savedInstanceState.getSerializable(Constants.destlatlng);
        }
        rws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TransportActivity.this, DirectionsActivity.class);
                i.putExtra(Constants.Source,src);
                i.putExtra(Constants.Destination,dest);
                i.putExtra(Constants.srclatlng,srckey);
                i.putExtra(Constants.destlatlng,destkey);
                startActivity(i);
            }
        });

    }
}
