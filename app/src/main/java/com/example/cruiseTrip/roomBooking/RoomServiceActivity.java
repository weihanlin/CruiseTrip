package com.example.cruiseTrip.roomBooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.adapters.ServicesAdapter;
import com.example.cruiseTrip.database.ServiceRepository;
import com.example.cruiseTrip.database.entity.RoomService;
import com.example.cruiseTrip.database.entity.Service;
import com.example.cruiseTrip.ui.InvoiceActivity;

import java.util.List;

public class RoomServiceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Service> services;
    private Button btn;

    int images[] = {R.drawable.img_roomservice_morningcall, R.drawable.img_roomservice_fooddelivery,
            R.drawable.img_roomservice_sensespa, R.drawable.img_roomservice_chillspa};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);

        ServiceRepository repository = new ServiceRepository(getApplication());
        services = repository.getAllServices();

        recyclerView = findViewById(R.id.roomService_recycle);
        ServicesAdapter adapter = new ServicesAdapter(this, images, services);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btn = findViewById(R.id.service_no);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(RoomServiceActivity.this, InvoiceActivity.class);
            startActivity(intent);
        });
    }
}
