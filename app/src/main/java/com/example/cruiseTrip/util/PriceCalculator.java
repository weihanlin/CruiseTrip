package com.example.cruiseTrip.util;

public class PriceCalculator {

    private int totalPrice;

    public int calPriceByTime(String roomType, int dayCount) {

        double typePrice;
        double priceTime = 0;

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
            priceTime = 0.1 * dayCount * dayCount + typePrice;
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }

        return (int)priceTime;
    }

    public int calRoomPrice(String roomType, int dayCount) {

        totalPrice += calPriceByTime(roomType, dayCount);

        return totalPrice;
    }

    public int calPeoplePrice(int peopleCount) {
        return peopleCount * 500;
    }
}
