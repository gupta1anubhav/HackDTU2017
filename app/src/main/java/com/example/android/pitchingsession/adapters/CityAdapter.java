package com.example.android.pitchingsession.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pitchingsession.R;
import com.example.android.pitchingsession.activities.CityInfo;
import com.example.android.pitchingsession.models.CitiesPojo.JsonCity;
import com.example.android.pitchingsession.models.City;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.R.attr.width;
import static com.example.android.pitchingsession.R.id.imageView;

/**
 * Created by Sushila on 10/7/2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyHolder> {
    Context context;
    ArrayList<JsonCity> cities = new ArrayList<>();

    public CityAdapter(Context context, ArrayList<JsonCity> cities) {
        this.context = context;
        this.cities = cities;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = li.inflate(R.layout.city_list_item,parent,false);
        return new MyHolder(itemView);
    }
    public void updateCities(ArrayList<JsonCity> cities){
        this.cities = cities;
        notifyDataSetChanged();
    }
    String returnurl(String name){
        String url;
        switch (name){
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
                url = "http://www.tourplan2india.com/wp-content/uploads/2013/01/sultanpur-bird-sanctuary.jpg";
                break;
        }
        return url;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
      holder.tvName.setText(cities.get(position).getName());
        final String url = returnurl(cities.get(position).getState());
        Picasso.with(context)
                        .load(url).fit().centerCrop()
                .into(holder.iv);
      holder.cvCity.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i = new Intent(context, CityInfo.class);
              i.putExtra("CityName",cities.get(position).getName());
              i.putExtra("Latitude",cities.get(position).getLat());
              i.putExtra("Longitude",cities.get(position).getLon());
              i.putExtra("url",url);
              context.startActivity(i);
          }
      });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        CardView cvCity;
    TextView tvName,tvLat,tvLong;
        ImageView iv;
        public MyHolder(View itemView) {
            super(itemView);
            cvCity = itemView.findViewById(R.id.cvCity);
            tvName = itemView.findViewById(R.id.tvName);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
