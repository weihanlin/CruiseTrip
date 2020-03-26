package com.example.cruiseTrip.roomBooking;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.entity.Room;
import com.example.cruiseTrip.database.RoomRepository;

import java.util.List;


public class RoomActivity extends AppCompatActivity {

    private RoomRepository roomRepository;
    private ImageView imageView;
    private LiveData<List<Room>> listLiveData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        LinearLayout linearLayout = new LinearLayout(this);
        setupLayout(linearLayout);
    }

    public void setupLayout(LinearLayout linearLayout) {
        roomRepository = new RoomRepository(this.getApplication());
        listLiveData = roomRepository.getAllRooms();
        listLiveData.observe(this, rooms -> {
            if (rooms != null && !rooms.isEmpty()) {
                for (Room room : rooms) {
                    imageView = new ImageView(RoomActivity.this);

                    if (room.isState() == true) {
                        imageView.setImageResource(R.drawable.ic_seat_normal);
                    } else if (room.isState() == false) {
                        imageView.setImageResource(R.drawable.ic_seat_sold);
                    }
                    linearLayout.addView(imageView);
                }
            }
        });
    }
}
