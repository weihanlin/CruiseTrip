package com.example.cruiseTrip;

import java.util.ArrayList;

public class PortAdventure {
    private String title;
    private boolean privateTour = false;
    private ArrayList<String> ageGroup;
    private ArrayList<Double> price;

    public PortAdventure(String title){
        this.title = title;
        this.ageGroup = new ArrayList<>();
        this.price = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPrivateTour() {
        return privateTour;
    }

    public void setPrivateTour(boolean privateTour) {
        this.privateTour = privateTour;
    }

    public ArrayList<String> getAgeGroup() {
        return ageGroup;
    }

    public void addAgeGroup(String ageGroup) {
        this.ageGroup.add(ageGroup);
    }

    public ArrayList<Double> getPrice() {
        return price;
    }

    public void addPrice(Double price) {
        this.price.add(price);
    }

}
