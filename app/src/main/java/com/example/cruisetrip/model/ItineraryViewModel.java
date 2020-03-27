package com.example.cruisetrip.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cruisetrip.database.ItinerariesRepository;
import com.example.cruisetrip.database.Itinerary;
import com.example.cruisetrip.database.SpecialActivity;

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


}
