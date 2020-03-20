package com.example.cruisetrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SADao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(SpecialActivity specialActivity);

    @Query("DELETE FROM SpecialActivity")
    void deleteAll();

    @Query("SELECT * FROM SpecialActivity")
    LiveData<List<SpecialActivity>> getAllActivities();

    @Query("SELECT * FROM SpecialActivity WHERE onboard = :onboard AND itinerary_id = :itinerary_id")
    LiveData<List<SpecialActivity>> getOnBoard(int itinerary_id, boolean onboard);

    @Query("SELECT * FROM SpecialActivity WHERE onboard = :onboard AND itinerary_id = :itinerary_id")
    LiveData<List<SpecialActivity>> getPortCall(int itinerary_id, boolean onboard);
}
