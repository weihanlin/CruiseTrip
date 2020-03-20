package com.example.cruisetrip.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReservationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Reservation Reservation);

    @Query("DELETE FROM Reservation")
    void deleteAll();

    @Query("SELECT * FROM Reservation")
    LiveData<List<Reservation>> getAllReservation();

    @Query("SELECT * FROM Reservation WHERE user_id = :user_id")
    LiveData<List<Reservation>> getReservationByUserID(int user_id);
}
