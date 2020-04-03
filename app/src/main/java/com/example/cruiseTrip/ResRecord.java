package com.example.cruiseTrip;

import java.util.Date;

public class ResRecord {
    private String title;
    private Date date;
    private int num_people;
    private double price;
    private String group;

    public ResRecord(String title, Date date, int num_people, double price, String group) {
        this.title = title;
        this.date = date;
        this.num_people = num_people;
        this.price = price;
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNum_people() {
        return num_people;
    }

    public void setNum_people(int num_people) {
        this.num_people = num_people;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
