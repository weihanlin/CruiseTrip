package com.example.cruisetrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Room room);

    @Query("DELETE FROM Room")
    void deleteAll();

    @Query("SELECT * FROM Room")
    LiveData<List<Room>> getAllRooms();

    @Query("UPDATE Room SET user_id = :user_id, state = 0 WHERE id = :id")
    int bookRoom(int id, int user_id);


}
