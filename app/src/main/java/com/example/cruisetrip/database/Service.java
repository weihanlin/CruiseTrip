package com.example.cruisetrip.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Service {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private double price;

    public Service(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
