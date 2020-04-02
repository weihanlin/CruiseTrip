package com.example.cruiseTrip.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.cruiseTrip.adapters.DaysAdapter;
import com.example.cruiseTrip.database.viewModel.ItineraryViewModel;
import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.SpecialActivity;
import com.example.cruiseTrip.roomBooking.RoomTypeActivity;

import java.util.ArrayList;
import java.util.List;

public class ActionActivity extends AppCompatActivity {

    private Button roomBookingBtn;
    private Button viewActivityBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        roomBookingBtn = findViewById(R.id.action_roomBooking_btn);
        viewActivityBtn = findViewById(R.id.action_activity_btn);

        SharedPreferences sharedPreferences = this.getSharedPreferences("SelectPref", MODE_PRIVATE);
        final int selItin = sharedPreferences.getInt("SELIT",1);

        final ArrayList<SpecialActivity> days = new ArrayList<>();

        ItineraryViewModel itineraryViewModel = new ItineraryViewModel(this.getApplication());
        itineraryViewModel.getDays(selItin).observe(this, specialActivities -> days.addAll(specialActivities));

        DaysAdapter adapter = new DaysAdapter(this, days);
        ListView listView = findViewById(R.id.theDay);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            //open another activity to show the port of call

        });

        final TextView titleView = findViewById(R.id.itTitle);
        titleView.setText(sharedPreferences.getString("ITNAME","PLACE"));

        roomBookingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ActionActivity.this, RoomTypeActivity.class);
            startActivity(intent);
        });
    }
}
