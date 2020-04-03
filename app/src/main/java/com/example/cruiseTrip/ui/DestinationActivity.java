package com.example.cruiseTrip.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cruiseTrip.adapters.ItinerariesAdapter;
import com.example.cruiseTrip.authentication.Session;
import com.example.cruiseTrip.database.UsersRepository;
import com.example.cruiseTrip.database.viewModel.ItineraryViewModel;
import com.example.cruiseTrip.R;

public class DestinationActivity extends AppCompatActivity {

    private ItineraryViewModel itineraryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        Session session = new Session(this);
        String username = session.getUsername();
        UsersRepository usersRepository = new UsersRepository(this.getApplication());

        int userId = 0;

        if(usersRepository.getUser(username) != null)
            userId = usersRepository.getUser(username).getId();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ItinerariesAdapter itinerariesAdapter = new ItinerariesAdapter(this, userId);
        recyclerView.setAdapter(itinerariesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itineraryViewModel = new ItineraryViewModel(this.getApplication());
        itineraryViewModel.getAllItineraries().observe(this, itinerariesAdapter::setItineraries);


    }
}
