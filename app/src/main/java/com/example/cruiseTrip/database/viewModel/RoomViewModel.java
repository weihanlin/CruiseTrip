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
    RoomRepository roomRepository;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        this.roomRepository = new RoomRepository(getApplication());
    }

    public List<Room> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    public Room getRoom(int id) {
        return roomRepository.getRoom(id);
    }

    public void bookRoomById(int id, int user_id) {
        roomRepository.bookRoom(id, user_id);
    }

//    public void bookRoomByName(int id, String username) {
//        roomRepository.bookRoom(id, username);
//    }
}
