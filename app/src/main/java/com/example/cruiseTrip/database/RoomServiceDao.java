package com.example.cruiseTrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomServiceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RoomService roomService);

    @Query("DELETE FROM RoomService")
    void deleteAll();

    @Query("SELECT * FROM RoomService")
    LiveData<List<RoomService>> getAllRoomServices();

    @Query("SELECT * FROM RoomService WHERE room_id = :room_id")
    LiveData<List<RoomService>> getRecordOfRoom(int room_id);
}
