package com.example.cruiseTrip.roomBooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.cruiseTrip.R;

import java.util.HashMap;
import java.util.Map;

public class RoomTypeActivity extends AppCompatActivity {

    private Map<String, LinearLayout> roomsType;
    private int peopleCount;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_type);

        peopleCount = getIntent().getIntExtra("peopleCount", 0);
        date = getIntent().getStringExtra("date");

        roomsType = new HashMap<>();
        roomsType.put("insideRoom", findViewById(R.id.insideRoom));
        roomsType.put("oceanRoom", findViewById(R.id.oceanRoom));
        roomsType.put("verandahRoom", findViewById(R.id.verandahRoom));
        roomsType.put("conciergeRoom", findViewById(R.id.conciergeRoom));

        roomsType.forEach((s, linearLayout) -> linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(RoomTypeActivity.this, RoomArrangementActivity.class);
            intent.putExtra("peopleCount", peopleCount);
            intent.putExtra("date", date);
            intent.putExtra("roomType", s);
            startActivity(intent);
        }));
    }
}
