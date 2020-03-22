package com.example.cruisetrip.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo
    private String name;

    @ColumnInfo
    private String email;

    @ColumnInfo
    private String phone;

    @ColumnInfo
    private String password;

    public User(String name, String hashedPassword ,String email, String phone) {
        this.name = name;
        this.password = hashedPassword;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
