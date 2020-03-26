package com.example.cruiseTrip.roomBooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.ui.WelcomeActivity;

public class ReservationActivity extends AppCompatActivity {

    private LinearLayout insideRoom;
    private LinearLayout oceanRoom;
    private LinearLayout verandahRoom;
    private LinearLayout conciergeRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        insideRoom = findViewById(R.id.insideRoom);
        oceanRoom = findViewById(R.id.oceanRoom);
        verandahRoom = findViewById(R.id.verandahRoom);
        conciergeRoom = findViewById(R.id.conciergeRoom);

        insideRoom.setOnClickListener(v -> {
            Intent i = new Intent(ReservationActivity.this, WelcomeActivity.class);
            startActivity(i);
        });

        oceanRoom.setOnClickListener(v -> {
            Intent i = new Intent(ReservationActivity.this, WelcomeActivity.class);
            startActivity(i);
        });

        verandahRoom.setOnClickListener(v -> {
            Intent i = new Intent(ReservationActivity.this, WelcomeActivity.class);
            startActivity(i);
        });

        conciergeRoom.setOnClickListener(v -> {
            Intent i = new Intent(ReservationActivity.this, WelcomeActivity.class);
            startActivity(i);
        });
    }
}
