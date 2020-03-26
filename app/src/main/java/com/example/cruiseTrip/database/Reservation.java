package com.example.cruiseTrip.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"),
        @ForeignKey(entity = SpecialActivity.class, parentColumns = "id", childColumns = "activity_id")},
        primaryKeys = {"user_id", "activity_id"})
public class Reservation {
    @ColumnInfo
    private int user_id;

    @ColumnInfo
    private int activity_id;

    @ColumnInfo
    private int numberPeople;

    public Reservation(int user_id, int activity_id){
        this.activity_id = activity_id;
        this.user_id = user_id;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }

    public int getUser_id() {
        return user_id;
    }


    public int getActivity_id() {
        return activity_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public void setActivity_id(int activity_id) {
        this.activity_id = activity_id;
    }
}
