package com.example.cruiseTrip.roomBooking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.adapters.GridItemAdapter;
import com.example.cruiseTrip.database.entity.Room;
import com.example.cruiseTrip.database.viewModel.RoomViewModel;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private List<Room> rooms;
    private GridView gridViewCol;
    private Button btnViewPrice;
    private TextView txtWarning;
    private ArrayList<Integer> selectedRoomsId = new ArrayList<>();;

    private int[] imgArray = {R.drawable.ic_seat_normal, R.drawable.ic_seat_selected, R.drawable.ic_seat_sold, R.drawable.ic_seat_unavailable};
    private int[] gridArray = {R.id.gridItemsCol1, R.id.gridItemsCol2, R.id.gridItemsCol3, R.id.gridItemsCol4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        btnViewPrice = findViewById(R.id.room_btn);
        txtWarning = findViewById(R.id.room_warning);

        String roomType = getIntent().getStringExtra("roomType");

        // Get all rooms from database
        RoomViewModel roomViewModel = new RoomViewModel(getApplication());
        rooms = roomViewModel.getAllRooms();
        // Divide the rooms into 4 columns
        int cols = rooms.size() / 4;
        // Set up layout for each room column
        for(int i = 0; i < 4; i++) {
            // each row: 1-5, 6-10, 11-15, 16-20
            List<Room> roomsCol = rooms.subList(i * cols, (i + 1) * cols);

            gridViewCol = findViewById(gridArray[i]);
            GridItemAdapter adapter = new GridItemAdapter(RoomActivity.this, roomsCol, imgArray, roomType);
            gridViewCol.setAdapter(adapter);
        }

        // set button onclick listener
        btnViewPrice.setOnClickListener(v -> {
            // get the clicked rooms id
            // GridItemAdapter.selectedRoomsId is a static variable from GridItemAdapter class
            selectedRoomsId = GridItemAdapter.selectedRoomsId;

            // if no rooms are clicked or selected, show the warning text, otherwise jump to the following activity
            if(!selectedRoomsId.isEmpty()) {
                Intent intent = new Intent(RoomActivity.this, PriceActivity.class);
                intent.putExtra("selectedRoomsId", selectedRoomsId);
                intent.putExtra("roomType", roomType);
                startActivity(intent);
            } else {
                txtWarning.setText("Please select at least one room!");
            }
        });
    }
}
