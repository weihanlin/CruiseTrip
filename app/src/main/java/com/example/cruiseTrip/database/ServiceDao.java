package com.example.cruiseTrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cruiseTrip.entity.Service;

import java.util.List;

@Dao
public interface ServiceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Service service);

    @Query("DELETE FROM Service")
    void deleteAll();

    @Query("SELECT * FROM Service")
    LiveData<List<Service>> getAllServices();
}
