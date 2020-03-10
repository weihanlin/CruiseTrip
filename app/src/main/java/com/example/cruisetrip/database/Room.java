package com.example.cruisetrip.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Itinerary.class, parentColumns = "id", childColumns = "itinerary_id"),
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id")})
public class Room {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String type;

    @ColumnInfo
    private boolean state;

    @ColumnInfo
    private int itinerary_id;

    @ColumnInfo
    private int user_id;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getItinerary_id() {
        return itinerary_id;
    }

    public void setItinerary_id(int itinerary_id) {
        this.itinerary_id = itinerary_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
