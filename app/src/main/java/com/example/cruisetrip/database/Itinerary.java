package com.example.cruisetrip.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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

    @ColumnInfo
    private String image;

    @Ignore
    public Itinerary(int id, String destination, Date startDate, int days, String image) {
        this.id = id;
        this.destination = destination;
        this.startDate = startDate;
        this.days = days;
        this.image = image;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
