package com.example.cruiseTrip.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.database.entity.Itinerary;

import java.util.List;

public class ItinerariesRepository {
    private ItinerariesDao itinerariesDao;
    private RoomDao roomDao;
    private SADao saDao;

    public ItinerariesRepository(Application application){
        CruiseDatabase db = CruiseDatabase.getDatabase(application);
        itinerariesDao = db.itinerariesDao();
        roomDao = db.roomDao();
        saDao = db.saDao();
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
    public LiveData<List<SpecialActivity>> getOnBoardActivities(Itinerary itinerary) {
        return saDao.getOnBoard(itinerary.getId(), true);
    }

    //Query all port activities for specific itinerary
    public LiveData<List<SpecialActivity>> getPortCall(Itinerary itinerary) {
        return saDao.getPortCall(itinerary.getId(), false);
    }

    public LiveData<List<SpecialActivity>> getDays(int itineraryID) {
        return saDao.getDays(itineraryID);
    }

}
