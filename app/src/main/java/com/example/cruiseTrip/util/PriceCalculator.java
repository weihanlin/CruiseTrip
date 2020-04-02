package com.example.cruiseTrip.util;

public class PriceCalculator {

    private int totalPrice;

    public int calPriceByTime(String roomType, int days) {

        double typePrice;
        double priceByTime = 0;
        int dayCount = 180 - days;

        switch (roomType) {
            case "insideRoom":
                typePrice = 2000.0;
                break;
            case "oceanRoom" :
                typePrice = 2500.0;
                break;
            case "verandahRoom" :
                typePrice = 3000.0;
                break;
            case "conciergeRoom" :
                typePrice = 4000.0;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + roomType);
        }

        try {
            priceByTime = 0.1 * dayCount * dayCount + typePrice;
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }

        return (int)priceByTime;
    }

    public int calRoomPrice(String roomType, int days) {

        totalPrice += calPriceByTime(roomType, days);

        return totalPrice;
    }

    public int calPeoplePrice(int peopleCount) {
        return peopleCount * 500;
    }
}
