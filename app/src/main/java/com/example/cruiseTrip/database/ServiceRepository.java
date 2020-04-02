package com.example.cruiseTrip.database;

import android.app.Application;

import com.example.cruiseTrip.database.entity.Service;

import java.util.List;

public class ServiceRepository {
    private ServiceDao serviceDao;
    private CruiseDatabase db;

    public ServiceRepository(Application application) {
        this.db = CruiseDatabase.getDatabase(application);
        this.serviceDao = db.serviceDao();
    }

    public List<Service> getAllServices () {
        return serviceDao.getAllServices();
    }
}
