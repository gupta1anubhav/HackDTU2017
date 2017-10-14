package com.example.android.pitchingsession.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.android.pitchingsession.R;
import com.example.android.pitchingsession.activities.TravelActivity;
import com.example.android.pitchingsession.api.ApiService;
import com.example.android.pitchingsession.models.CitiesPojo.JsonCity;
import com.example.android.pitchingsession.utils.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.x;
import static android.R.attr.y;
import static android.content.ContentValues.TAG;

import static com.example.android.pitchingsession.R.id.actvCity;
import static com.example.android.pitchingsession.R.id.pbCity;


public class SourceDestinaton extends Fragment {
    ArrayList<JsonCity> cities = new ArrayList<>();
    Spinner source,destination;
  Button btnCont;
    int x,y;
    String src,dest;
    String array[] = new String[]{
            "","","","","","","","","","","",""
    };

  boolean flag1 = false;
  boolean flag2 = false;
    public SourceDestinaton() {
        // Required empty public constructor
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
    boolean checkspinner(){
        if(src.equals(dest)){
            return false;
        }
        else return true;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_source_destinaton, container, false);
        btnCont = rootView.findViewById(R.id.btnCont);
        source = rootView.findViewById(R.id.source);
        destination = rootView.findViewById(R.id.destination);

        final Intent intent = new Intent(getContext(), TravelActivity.class);
        ApiService.getApi().getCities().enqueue(new Callback<ArrayList<JsonCity>>() {
            @Override
            public void onResponse(Call<ArrayList<JsonCity>> call, Response<ArrayList<JsonCity>> response) {
                Log.d(TAG, "onResponse: "+response.body().get(0).getLat());
               /* src = response.body().get(0).getLat()+","+response.body().get(0).getLon();
                Log.d(TAG, "onResponse: "+response.body().get(1).getLat());
                dest = response.body().get(1).getLat()+","+response.body().get(1).getLon();*/
                for (int i = 0; i < response.body().size(); i++) {
                    Log.d(TAG, "onResponse: searching");

                    cities.add(response.body().get(i));
                    array[i] = response.body().get(i).getName();
                    if(i==11){
                        break;
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<JsonCity>> call, Throwable t) {

            }
        });

        final ArrayAdapter adapter = new ArrayAdapter (getContext(),android.R.layout.simple_spinner_dropdown_item,array);
        source.setAdapter(adapter);
        //source.setSelection(0);
        destination.setAdapter(adapter);
        //destination.setSelection(0);
       source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               Log.d(TAG, "onItemSelected: "+adapterView.getItemAtPosition(i));
               if(adapterView.getItemAtPosition(i)!=""){
                   src = adapterView.getItemAtPosition(i).toString();
                   x = checkcity(adapterView.getItemAtPosition(i).toString());
               }
               else{
                   Toast.makeText(getContext(),"Select Source",Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
               source.setSelection(0);
           }
       });
        destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemSelected: "+adapterView.getItemAtPosition(i));
                if(adapterView.getItemAtPosition(i)!=""){
                    dest = adapterView.getItemAtPosition(i).toString();
                    y = checkcity(adapterView.getItemAtPosition(i).toString());
                }
                else {
                    //destination.setSelection(0);
                    Toast.makeText(getContext(),"Select Destination",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // destination.setSelection(0);
            }
        });
        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: "+cities.get(y).getLat()+","+cities.get(y).getLon());
                if(checkspinner()){
                intent.putExtra(Constants.Source,cities.get(x).getName());
                intent.putExtra(Constants.Destination,cities.get(y).getName());
                intent.putExtra(Constants.srclatlng,cities.get(x).getLat()+","+cities.get(x).getLon() );
                intent.putExtra(Constants.destlatlng,cities.get(y).getLat()+","+cities.get(y).getLon());

                startActivity(intent);
            }
            else {
                    Toast.makeText(getContext(),"Source and Destination Can't be same",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}
