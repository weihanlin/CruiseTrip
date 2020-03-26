package com.example.cruiseTrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.cruiseTrip.entity.User;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("INSERT INTO User (name, password, email, phone) VALUES (:name, :password, :email, :phone)")
    void insert(String name, String password, String email, String phone);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

}
