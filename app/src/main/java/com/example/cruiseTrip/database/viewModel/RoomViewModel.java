package com.example.cruiseTrip.database.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.database.RoomRepository;
import com.example.cruiseTrip.database.entity.Room;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private List<Room> rooms;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        RoomRepository roomRepository = new RoomRepository(getApplication());
        rooms = roomRepository.getAllRooms();
    }

    public List<Room> getAllRooms() {
        return rooms;
    }
}
