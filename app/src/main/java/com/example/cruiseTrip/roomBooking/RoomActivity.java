package com.example.cruiseTrip.roomBooking;

import android.database.DataSetObservable;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.adapters.GridItemAdapter;
import com.example.cruiseTrip.database.RoomRepository;
import com.example.cruiseTrip.entity.Room;
import com.example.cruiseTrip.viewModel.RoomViewModel;

import java.util.List;

public class RoomActivity extends AppCompatActivity {

    private List<Room> rooms;
    private GridView gridView;
    private LiveData<List<Room>> listLiveData;
    int imageNum = (R.drawable.ic_seat_normal);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        gridView = findViewById(R.id.gridItems);

        RoomViewModel roomViewModel = new RoomViewModel(getApplication());
        listLiveData = roomViewModel.getAllRooms();
        listLiveData.observe(this, rooms -> {

            GridItemAdapter adapter = new GridItemAdapter(RoomActivity.this, rooms, imageNum);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener((parent, view, position, id) -> {
            });
        });
    }
}
