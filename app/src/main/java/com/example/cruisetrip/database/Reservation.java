package com.example.cruisetrip.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"),
        @ForeignKey(entity = Itinerary.class, parentColumns = "id", childColumns = "itinerary_id"),
        @ForeignKey(entity = SpecialActivity.class, parentColumns = "id", childColumns = "activity_id")},
        primaryKeys = {"user_id", "itinerary_id", "activity_id"})
public class Reservation {
    @ColumnInfo
    private int user_id;

    @ColumnInfo
    private int itinerary_id;

    @ColumnInfo
    private int activity_id;

    @ColumnInfo
    private int numberPeople;

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }
}
