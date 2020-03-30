package com.example.cruiseTrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cruiseTrip.database.entity.Itinerary;

import java.util.List;

@Dao
public interface ItinerariesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Itinerary itinerary);

    @Query("DELETE FROM Itinerary")
    void deleteAll();

    @Query("SELECT * FROM Itinerary")
    LiveData<List<Itinerary>> getAllItineraries();
}
