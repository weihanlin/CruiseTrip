package com.example.cruiseTrip.roomBooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.adapters.GridItemAdapter;
import com.example.cruiseTrip.database.entity.Room;
import com.example.cruiseTrip.database.viewModel.RoomViewModel;
import com.example.cruiseTrip.ui.WelcomeActivity;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private List<Room> rooms;
    private GridView gridViewCol;

    int[] imgArray = {R.drawable.ic_seat_normal, R.drawable.ic_seat_selected, R.drawable.ic_seat_sold, R.drawable.ic_seat_unavailable};
    int[] gridArray = {R.id.gridItemsCol1, R.id.gridItemsCol2, R.id.gridItemsCol3, R.id.gridItemsCol4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        String roomType = getIntent().getStringExtra("roomType");


        RoomViewModel roomViewModel = new RoomViewModel(getApplication());
        rooms = roomViewModel.getAllRooms();

        for(int i = 0; i < 4; i++) {
            List<Room> roomsCol = rooms.subList(i * 5, (i + 1) * 5);

            gridViewCol = findViewById(gridArray[i]);
            GridItemAdapter adapter = new GridItemAdapter(RoomActivity.this, roomsCol, imgArray, roomType);
            gridViewCol.setAdapter(adapter);

            int finalI = i;
            gridViewCol.setOnItemClickListener((parent, view, position, id) -> {
                if (rooms.get(position + finalI * 5).getType().equals(roomType)) {

                    // Mark to change
                    Intent intent = new Intent(RoomActivity.this, WelcomeActivity.class);
                    RoomActivity.this.startActivity(intent);
                }
            });
        }
    }
}
