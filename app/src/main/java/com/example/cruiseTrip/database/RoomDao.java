package com.example.cruiseTrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cruiseTrip.entity.Room;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Room room);

    @Query("DELETE FROM Room")
    void deleteAll();

    @Query("SELECT * FROM room")
    LiveData<List<Room>> getAllRooms();

    @Query("UPDATE Room SET itinerary_id = :itinerary_id WHERE id = :id")
    int updateItinerary(int id, int itinerary_id);

    @Query("UPDATE Room SET user_id = :user_id, state = 0 WHERE id = :id")
    int bookRoom(int id, int user_id);

    @Query("SELECT * FROM Room WHERE user_id = :user_id")
    LiveData<List<Room>> getBookedRooms(int user_id);


}
