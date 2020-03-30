package com.example.cruiseTrip.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.database.entity.Room;

import java.util.List;

public class RoomRepository {
    private RoomDao roomDao;
    private CruiseDatabase db;

    public RoomRepository(Application application) {
        this.db = CruiseDatabase.getDatabase(application);
        this.roomDao = db.roomDao();
    }

    public List<Room> getAllRooms () {
        return roomDao.getAllRooms();
    }

    public LiveData<List<Room>> getBookedRooms(int userId) {
        return roomDao.getBookedRooms(userId);
    }

    public void bookRoom(int id, int user_id) {
        roomDao.bookRoom(id, user_id);
    }
}
