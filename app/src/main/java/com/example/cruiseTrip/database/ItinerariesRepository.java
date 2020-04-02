package com.example.cruiseTrip.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.database.entity.Itinerary;

import java.util.List;

public class ItinerariesRepository {
    ItinerariesDao itinerariesDao;
    RoomDao roomDao;
    SADao saDao;
    ReservationDao reservationDao;

    public ItinerariesRepository(Application application){
        CruiseDatabase db = CruiseDatabase.getDatabase(application);
        itinerariesDao = db.itinerariesDao();
        roomDao = db.roomDao();
        saDao = db.saDao();
        reservationDao = db.reservationDao();
    }

    public void insert(final Itinerary itinerary){
        CruiseDatabase.databaseWriteExecutor.execute(() ->
                itinerariesDao.insert(itinerary));
    }

    //Query all itineraries
    public LiveData<List<Itinerary>> getAllItineraries(){
        return itinerariesDao.getAllItineraries();
    }

    //Query all on board activities for specific itinerary
    public LiveData<List<SpecialActivity>> getOnBoardActivities(int itineraryID) {
        return saDao.getOnBoard(itineraryID, true);
    }

    //Query all port activities for specific itinerary
    public LiveData<List<SpecialActivity>> getPortCall(int itineraryID, Date date) {
        return saDao.getPortCall(itineraryID, false, date);
    }

    public LiveData<List<SpecialActivity>> getDays(int itineraryID) {
        return saDao.getDays(itineraryID);
    }

    public void reserve(final Reservation reservation){
        CruiseDatabase.databaseWriteExecutor.execute(() -> {
            reservationDao.insert(reservation);
        });
    }

    public LiveData<List<Reservation>> getReservation(int user_id){
        return reservationDao.getReservationByUserID(user_id);
    }

    public LiveData<List<Reservation>> getReservation(){
        return reservationDao.getAllReservation();
    }


    public void cancel_reserve(int user, int act){
        CruiseDatabase.databaseWriteExecutor.execute(() -> {
            reservationDao.delete(user, act);
        });
    }



    public int numRecordByActID(int act){
        List<Reservation> reservations = reservationDao.getRecordByActID(act).getValue();

        if(reservations != null)
            return reservations.size();
        else
            return 0;
    }
}
