package com.example.cruisetrip.GUI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruisetrip.R;
import com.example.cruisetrip.database.Itinerary;

import java.util.List;

public class ItinerariesAdapter extends RecyclerView.Adapter<ItinerariesAdapter.ItineraryHolder> {

    private final LayoutInflater layoutInflater;
    private List<Itinerary> mItineraries;
    private Context context;

    public ItinerariesAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItineraryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ItineraryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryHolder holder, int position) {
        if(mItineraries != null){
            Itinerary itinerary = mItineraries.get(position);



            holder.desImg.setImageDrawable(context.getDrawable(context.getResources().getIdentifier(itinerary.getImage(),"drawable",context.getPackageName())));

            holder.destination.setText(itinerary.getDestination());

            holder.destination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), AlaskaActivity.class);
                    v.getContext().startActivity(i);
                }
            });
        }
        else{
            holder.desImg.setVisibility(View.INVISIBLE);
            holder.destination.setText("NO DATA");
        }


    }

    @Override
    public int getItemCount() {
        if(mItineraries != null)
            return mItineraries.size();

        return 0;
    }

    public void setItineraries(List<Itinerary> itineraries){
        this.mItineraries = itineraries;
        notifyDataSetChanged();
    }


    class ItineraryHolder extends RecyclerView.ViewHolder{

        public TextView destination;
        public ImageView desImg;

        public ItineraryHolder(@NonNull View itemView) {
            super(itemView);
            destination = itemView.findViewById(R.id.destination);
            desImg = itemView.findViewById(R.id.desImg);
        }
    }

}
