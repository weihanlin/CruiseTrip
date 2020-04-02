package com.example.cruisetrip.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cruisetrip.database.ItinerariesRepository;
import com.example.cruisetrip.database.Itinerary;
import com.example.cruisetrip.database.Reservation;
import com.example.cruisetrip.database.SpecialActivity;

import java.util.Date;
import java.util.List;

public class  ItineraryViewModel extends AndroidViewModel {

    private ItinerariesRepository itinerariesRepository;
    private LiveData<List<Itinerary>> allItineraries;

    public ItineraryViewModel(@NonNull Application application) {
        super(application);
        itinerariesRepository = new ItinerariesRepository(application);
        allItineraries = itinerariesRepository.getAllItineraries();
    }

    public LiveData<List<Itinerary>> getAllItineraries() {
        return allItineraries;
    }

    public LiveData<List<SpecialActivity>> getDays(int itineraryID) {
        return itinerariesRepository.getDays(itineraryID);
    }

    public LiveData<List<SpecialActivity>> getActivities(int itineraryID) {
        return itinerariesRepository.getOnBoardActivities(itineraryID);
    }

    public LiveData<List<SpecialActivity>> getPortAdventure(int itineraryID, Date date){
        return itinerariesRepository.getPortCall(itineraryID,date);
    }

    public void reserveActivity(int userid, int activityid){
        Reservation reservation = new Reservation(userid, activityid);
        itinerariesRepository.reserve(reservation);
    }

    public LiveData<List<Reservation>> getReservation(int user_id){
        return itinerariesRepository.getReservation(user_id);
    }

    public LiveData<List<Reservation>> getReservation(){
        return itinerariesRepository.getReservation();
    }

    public void cancelReservation(int user, int act){
        itinerariesRepository.cancel_reserve(user, act);
    }

    public int getNumReservation(int act){
        return itinerariesRepository.numRecordByActID(act);
    }

}
