package com.example.cruiseTrip.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.entity.Room;

import java.util.List;

public class RoomRepository {
    RoomDao roomDao;
    private CruiseDatabase db;

    public RoomRepository(Application application){
        this.db = CruiseDatabase.getDatabase(application);
        this.roomDao = db.roomDao();
    }

    public LiveData<List<Room>> getAllRooms() {
        return roomDao.getAllRooms();
    }

    public LiveData<List<Room>> getBookedRooms(int userId) {
        return roomDao.getBookedRooms(userId);
    }

    public void bookRoom(int id, int user_id) {
        roomDao.bookRoom(id, user_id);
    }
}
