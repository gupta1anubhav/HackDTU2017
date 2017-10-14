package com.example.android.pitchingsession.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import com.example.android.pitchingsession.R;
import com.example.android.pitchingsession.adapters.CityAdapter;
import com.example.android.pitchingsession.api.ApiService;
import com.example.android.pitchingsession.models.CitiesPojo.JsonCity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class CityActivity extends AppCompatActivity {
ArrayList<JsonCity> cities = new ArrayList<>();
ProgressBar pbCity;
RecyclerView rvCity;
AutoCompleteTextView actvCity;
 String[] accities = new String[]{""
         ,""
         ,""
         ,""
         ,""
         ,""
         ,""
         ,""
         ,""
         ,""
         ,""
         ,""
 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        pbCity = (ProgressBar) findViewById(R.id.pbCity);
        rvCity = (RecyclerView) findViewById(R.id.rvCity);
        actvCity = (AutoCompleteTextView) findViewById(R.id.actvCity);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,accities);
        pbCity.setIndeterminate(true);
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        final CityAdapter cityAdapter = new CityAdapter(this,cities);
        rvCity.setAdapter(cityAdapter);
        ApiService.getApi().getCities().enqueue(new Callback<ArrayList<JsonCity>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonCity>> call, Response<ArrayList<JsonCity>> response) {
                for (int i= 0; i<12;i++){
                    cities.add(response.body().get(i));
                    accities[i] = response.body().get(i).getName();
                }
                cityAdapter.updateCities(cities);
                pbCity.setIndeterminate(false);
                pbCity.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ArrayList<JsonCity>> call, Throwable t) {

            }
        });

        actvCity.setThreshold(1);
        actvCity.setAdapter(adapter);
        actvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i1 = new Intent(CityActivity.this, CityInfo.class);
                //Log.d("hi", "onItemClick: "+i+l);
                int x= checkcity(actvCity.getText().toString());
                i1.putExtra("CityName",cities.get(x).getName());
                i1.putExtra("Latitude",cities.get(x).getLat());
                i1.putExtra("Longitude",cities.get(x).getLon());
                String url = returnurl(cities.get(x).getState());
                i1.putExtra("url",url);
                startActivity(i1);
            }
        });
    }
    int checkcity(String search){
        int i =0;
        while(true){
            if(cities.get(i).getName().equals(search)){
               return i;
            }
            i++;
        }
    }
    String returnurl(String name){
        String url;
        switch (name){
            case "Uttar Pradesh":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Taj-Mahal-India.jpg";
                break;
            case "West Bengal":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Kurseong.jpg";
                break;
            case "Rajasthan":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Jaisalmer-Rajasthan.jpg";
                break;
            case "Himachal Pradesh":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/shimla-himachal-pradesh.jpg";
                break;
            case "Tamil Nadu":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Central-Station-of-Chennai.jpg";
                break;
            case "Bihar":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/mahabodhi-templ-bihar.jpg";
                break;
            case "Chhattisgarh":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Chittrakote-WaterFalls.jpg";
                break;
            case "Goa":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Palolem-Beach-Goa.jpg";
                break;
            case "Haryana":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/sultanpur-bird-sanctuary.jpg";
                break;
            case "Delhi":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/lotus-temple-delhi.jpg";
                break;
            case "Maharashtra":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/mumbai-gateway-of-India.jpg";
                break;
            case "Gujarat":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Gandhinagar-Gujarat.jpg";
                break;
            case "Karnataka":
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/bangalore-palace.jpg";
                break;
            default:
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/Jaisalmer-Rajasthan.jpg";
                break;
        }
        return url;
    }
}
