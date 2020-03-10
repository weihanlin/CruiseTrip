package com.example.cruisetrip.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Itinerary {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo
    private String destination;

    @ColumnInfo
    private Date startDate;

    @ColumnInfo
    private int days;

    public Itinerary(String destination, Date startDate, int days) {
        this.destination = destination;
        this.startDate = startDate;
        this.days = days;
    }

    public Itinerary(String destination) {
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
