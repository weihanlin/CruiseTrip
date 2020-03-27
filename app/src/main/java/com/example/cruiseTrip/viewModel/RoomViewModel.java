package com.example.cruiseTrip.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.database.RoomRepository;
import com.example.cruiseTrip.entity.Room;

import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private LiveData<List<Room>> roomsLiveData;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        RoomRepository roomRepository = new RoomRepository(getApplication());
        roomsLiveData = roomRepository.getAllRooms();
    }

    public LiveData<List<Room>> getAllRooms() {
        return roomsLiveData;
    }
}
