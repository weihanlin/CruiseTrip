package com.example.cruiseTrip.roomBooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cruiseTrip.R;

import java.util.HashMap;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity {

    private Map<String, LinearLayout> roomsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        roomsType = new HashMap<>();
        roomsType.put("insideRoom", findViewById(R.id.insideRoom));
        roomsType.put("oceanRoom", findViewById(R.id.oceanRoom));
        roomsType.put("verandahRoom", findViewById(R.id.verandahRoom));
        roomsType.put("conciergeRoom", findViewById(R.id.conciergeRoom));

        roomsType.forEach((s, linearLayout) -> {
            linearLayout.setOnClickListener(v -> {
                Intent i = new Intent(ReservationActivity.this, RoomActivity.class);
                i.putExtra("roomType", s);
                startActivity(i);
            });
        });
    }
}
