package com.example.cruisetrip.GUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruisetrip.model.ItinerariesAdapter;
import com.example.cruisetrip.model.ItineraryViewModel;
import com.example.cruisetrip.R;
import com.example.cruisetrip.database.Itinerary;

import java.util.List;

public class DestinationActivity extends AppCompatActivity {

    private ItineraryViewModel itineraryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ItinerariesAdapter itinerariesAdapter = new ItinerariesAdapter(this);
        recyclerView.setAdapter(itinerariesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itineraryViewModel = new ItineraryViewModel(this.getApplication());
        itineraryViewModel.getAllItineraries().observe(this, itinerariesAdapter::setItineraries);
    }
}
