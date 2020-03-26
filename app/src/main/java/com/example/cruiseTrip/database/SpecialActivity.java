package com.example.cruiseTrip.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cruiseTrip.entity.Itinerary;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Itinerary.class, parentColumns = "id", childColumns = "itinerary_id")})
public class SpecialActivity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo
    private String title;

    @NonNull
    @ColumnInfo
    private boolean onboard;

    @NonNull
    @ColumnInfo
    private boolean reservation;

    @ColumnInfo
    private double price;

    @ColumnInfo
    private int maxNumber;

    @ColumnInfo
    private Date start;

    @ColumnInfo
    private Integer itinerary_id;

    @ColumnInfo
    private String description;


    public SpecialActivity(@NonNull String title, boolean onboard, boolean reservation) {
        this.title = title;
        this.onboard = onboard;
        this.reservation = reservation;
    }

    @Ignore
    public SpecialActivity(int id, @NonNull String title, boolean onboard, boolean reservation, Integer itinerary_id) {
        this.id = id;
        this.itinerary_id = itinerary_id;
        this.title = title;
        this.onboard = onboard;
        this.reservation = reservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public boolean isOnboard() {
        return onboard;
    }

    public void setOnboard(boolean onboard) {
        this.onboard = onboard;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getItinerary_id() {
        return itinerary_id;
    }

    public void setItinerary_id(Integer itinerary_id) {
        this.itinerary_id = itinerary_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
