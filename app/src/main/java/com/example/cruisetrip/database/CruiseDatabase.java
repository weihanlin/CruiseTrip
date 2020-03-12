package com.example.cruisetrip.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Itinerary.class, SpecialActivity.class,
        Room.class, Reservation.class, Service.class, RoomService.class},
        version = 1, exportSchema = false)
public abstract class CruiseDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();

    private static volatile CruiseDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CruiseDatabase getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (CruiseDatabase.class) {
                if(INSTANCE == null)
                    INSTANCE = androidx.room.Room.databaseBuilder(context.getApplicationContext(),
                            CruiseDatabase.class, "cruise_db").build();
            }
        }

        return INSTANCE;
    }
}
