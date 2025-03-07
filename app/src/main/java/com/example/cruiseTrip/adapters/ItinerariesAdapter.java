package com.example.cruiseTrip.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruiseTrip.roomBooking.RoomArrangementActivity;
import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.entity.Itinerary;
import com.example.cruiseTrip.roomBooking.RoomTypeActivity;
import com.example.cruiseTrip.ui.ActionActivity;
import com.example.cruiseTrip.ui.DestinationActivity;
import com.example.cruiseTrip.ui.ReservationRecord;

import java.util.List;

public class ItinerariesAdapter extends RecyclerView.Adapter<ItinerariesAdapter.ItineraryHolder> {

    private final LayoutInflater layoutInflater;
    private List<Itinerary> mItineraries;
    private Context context;
    private SharedPreferences sharedPreferences;
    private int userId = 0;

    public ItinerariesAdapter(Context context, int userId) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        sharedPreferences = context.getSharedPreferences("SelectPref", Context.MODE_PRIVATE);
        this.userId = userId;
    }

    @NonNull
    @Override
    public ItineraryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_destination, parent, false);
        return new ItineraryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItineraryHolder holder, int position) {
        if(mItineraries != null){
            Itinerary itinerary = mItineraries.get(position);

            holder.desImg.setImageDrawable(context.getDrawable(context.getResources().getIdentifier(
                    itinerary.getImage(),"drawable",context.getPackageName())));

            holder.destination.setText(itinerary.getDestination());

            holder.destination.setOnClickListener(v -> {
                sharedPreferences.edit().putInt("SELIT",itinerary.getId()).apply();
                sharedPreferences.edit().putString("ITNAME",itinerary.getDestination()).apply();

                Intent i = new Intent(v.getContext(), ActionActivity.class);
                v.getContext().startActivity(i);
            });

            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences.edit().putInt("SELIT",itinerary.getId()).apply();
                    Intent i = new Intent(v.getContext(), ReservationRecord.class);
                    v.getContext().startActivity(i);
                }
            });
        }
        else{
            holder.desImg.setVisibility(View.INVISIBLE);
            holder.destination.setText("NO DATA");
        }

        Log.d("???", "userid:" + userId );
        if(userId == 0) {
            holder.btn.setVisibility(View.GONE);
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
        public Button btn;

        public ItineraryHolder(@NonNull View itemView) {
            super(itemView);
            destination = itemView.findViewById(R.id.destination);
            desImg = itemView.findViewById(R.id.desImg);
            btn = itemView.findViewById(R.id.button);
        }
    }
}
