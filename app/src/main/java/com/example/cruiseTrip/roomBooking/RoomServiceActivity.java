package com.example.cruiseTrip.roomBooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.adapters.ServicesAdapter;
import com.example.cruiseTrip.database.ServiceRepository;
import com.example.cruiseTrip.database.entity.Service;

import java.util.List;

public class RoomServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Service> services;

    int images[] = {R.drawable.img_roomservice_morningcall, R.drawable.img_roomservice_fooddelivery,
            R.drawable.img_roomservice_sensespa, R.drawable.img_roomservice_chillspa};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_onboard);

        ServiceRepository repository = new ServiceRepository(getApplication());
        services = repository.getAllServices();

        recyclerView = findViewById(R.id.roomService_recycle);
        ServicesAdapter adapter = new ServicesAdapter(this, images, services);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
