package com.example.android.pitchingsession.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pitchingsession.R;

public class LocateActivity extends AppCompatActivity implements View.OnClickListener {
TextView tv1,tv2;
ImageView ivAtm,ivShop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);
        tv1 = (TextView) findViewById(R.id.tv1);
        findViewById(R.id.ivAtm).setOnClickListener(this);
        findViewById(R.id.ivShop).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.ivAtm:
            i = new Intent(LocateActivity.this,NearbyPlaces.class);
             i.putExtra("Type","atm");
             i.putExtra("SelfLocate",true);
                startActivity(i);
                break;
            case R.id.ivShop:
                i = new Intent(LocateActivity.this,NearbyPlaces.class);
                i.putExtra("Type","shopping_mall");
                i.putExtra("SelfLocate",true);
                startActivity(i);
                break;
        }
    }
}
