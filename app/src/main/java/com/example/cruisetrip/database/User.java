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

    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
