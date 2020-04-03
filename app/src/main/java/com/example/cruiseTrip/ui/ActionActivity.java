package com.example.cruiseTrip.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.adapters.DaysAdapter;
import com.example.cruiseTrip.authentication.Session;
import com.example.cruiseTrip.database.SpecialActivity;
import com.example.cruiseTrip.database.UsersRepository;
import com.example.cruiseTrip.database.entity.Reservation;
import com.example.cruiseTrip.database.viewModel.ItineraryViewModel;
import com.example.cruiseTrip.portActivity.ActivityAdapter;
import com.example.cruiseTrip.portActivity.PortAdvActivity;
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

        roomBookingBtn = findViewById(R.id.bookRoomBtn);
//        viewActivityBtn = findViewById(R.id.action_activity_btn);

        roomBookingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ActionActivity.this, RoomTypeActivity.class);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = this.getSharedPreferences("SelectPref", MODE_PRIVATE);
        final int selItin = sharedPreferences.getInt("SELIT",1);

        Session session = new Session(this);

        String username = session.getUsername();

        final ArrayList<SpecialActivity> days = new ArrayList<>();
        final ArrayList<SpecialActivity> activities = new ArrayList<>();
        final ArrayList<Reservation> reservation = new ArrayList<>();

        ItineraryViewModel itineraryViewModel = new ItineraryViewModel(this.getApplication());
        itineraryViewModel.getDays(selItin).observe(this, specialActivities -> days.addAll(specialActivities));

        itineraryViewModel.getActivities(selItin).observe(this, new Observer<List<SpecialActivity>>() {
            @Override
            public void onChanged(List<SpecialActivity> specialActivities) {
                activities.clear();
                activities.addAll(specialActivities);

            }
        });



        final TextView titleView = findViewById(R.id.itTitle);
        titleView.setText(sharedPreferences.getString("ITNAME","PLACE"));


        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.action_list, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        ListView listView = findViewById(R.id.theDay);
        listView.addFooterView(new View(this));
        DaysAdapter adapter = new DaysAdapter(this, days);
        ActivityAdapter adapter1 = new ActivityAdapter(this, activities, reservation);

        itineraryViewModel.getReservation().observe(this, new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                reservation.clear();
                reservation.addAll(reservations);
                listView.setAdapter(adapter1);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            if(username.equals("")){
                                Toast.makeText(getApplicationContext(),"You need to log in first", Toast.LENGTH_LONG).show();
                                return;
                            }

                            sharedPreferences.edit().putLong("DATE", days.get(position).getStart().getTime()).apply();

                            Intent i = new Intent(ActionActivity.this, PortAdvActivity.class);
                            startActivity(i);

                        }
                    });
                }
                else if(position == 1) {
                    listView.setAdapter(adapter1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        roomBookingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ActionActivity.this, RoomTypeActivity.class);
            startActivity(intent);
        });
    }

}
