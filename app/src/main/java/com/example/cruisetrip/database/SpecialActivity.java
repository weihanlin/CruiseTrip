package com.example.cruisetrip.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
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

    public SpecialActivity(@NonNull String title, boolean onboard, boolean reservation) {
        this.title = title;
        this.onboard = onboard;
        this.reservation = reservation;
    }

    public int getId() {
        return id;
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
}
