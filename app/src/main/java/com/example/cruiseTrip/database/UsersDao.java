package com.example.cruiseTrip.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cruiseTrip.database.entity.User;

import java.util.List;

@Dao
public interface UsersDao {

    @Query("INSERT INTO User (name, password, email, phone) VALUES (:name, :password, :email, :phone)")
    void insertUser(String name, String password, String email, String phone);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Query("DELETE FROM User")
    void deleteAll();

    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE name = :name")
    User getUser(String name);
}
