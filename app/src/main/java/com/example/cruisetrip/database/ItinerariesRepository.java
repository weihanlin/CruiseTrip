package com.example.cruisetrip.database;

import android.app.Application;

public class ItinerariesRepository {
    ItinerariesDao itinerariesDao;

    public ItinerariesRepository(Application application){
        CruiseDatabase db = CruiseDatabase.getDatabase(application);
        itinerariesDao = db.itinerariesDao();
    }

    public void insert(final Itinerary itinerary){
        CruiseDatabase.databaseWriteExecutor.execute(() -> {
            itinerariesDao.insert(itinerary);
        });
    }
}
