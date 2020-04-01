package com.example.cruiseTrip.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cruiseTrip.database.entity.Itinerary;
import com.example.cruiseTrip.database.entity.Reservation;
import com.example.cruiseTrip.database.entity.Room;
import com.example.cruiseTrip.database.entity.RoomService;
import com.example.cruiseTrip.database.entity.Service;
import com.example.cruiseTrip.database.entity.User;
import com.example.cruiseTrip.util.Converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Itinerary.class, SpecialActivity.class,
        Room.class, Reservation.class, Service.class, RoomService.class},
        version = 10, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class CruiseDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();
    public abstract ItinerariesDao itinerariesDao();
    public abstract RoomDao roomDao();
    public abstract SADao saDao();
    public abstract ReservationDao reservationDao();
    public abstract RoomServiceDao roomServiceDao();
    public abstract ServiceDao serviceDao();

    private static volatile CruiseDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CruiseDatabase getDatabase(final Context context){
        if(INSTANCE == null) {
            synchronized (CruiseDatabase.class) {
                if(INSTANCE == null) {
                    Log.d("DATABASE", "INSTANCE is NULL");
                    INSTANCE = androidx.room.Room.databaseBuilder(context,
                            CruiseDatabase.class, "cruise_db")
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            Log.d("DATABASE","OPEN sRoomDatabaseCallback");
            databaseWriteExecutor.execute(()->{
                UsersDao dao = INSTANCE.usersDao();
                ItinerariesDao itinerariesDao = INSTANCE.itinerariesDao();
                RoomDao roomDao = INSTANCE.roomDao();
                SADao saDao = INSTANCE.saDao();
                ServiceDao serviceDao = INSTANCE.serviceDao();
                RoomServiceDao roomServiceDao = INSTANCE.roomServiceDao();
                ReservationDao reservationDao = INSTANCE.reservationDao();

                reservationDao.deleteAll();
                roomServiceDao.deleteAll();
                serviceDao.deleteAll();
                saDao.deleteAll();
                roomDao.deleteAll();
                dao.deleteAll();
                itinerariesDao.deleteAll();

                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    itinerariesDao.insert(new Itinerary(1,"Alaska", fmt.parse("2020-05-20"), 5, "img_trip_alaska"));
                    itinerariesDao.insert(new Itinerary(2,"Europe", fmt.parse("2020-09-20"), 5, "img_trip_europe"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dao.insert("admin", "admin123", "1@gmail.com", "7886542234");
                dao.insert("another", "other123", "2@gmail.com", "7884587288");
                roomDao.insert(new Room(201,"conciergeRoom",1));
                roomDao.insert(new Room(206,"insideRoom",1));
                roomDao.insert(new Room(207,"insideRoom",1));
                roomDao.insert(new Room(202,"verandahRoom",1));
                roomDao.insert(new Room(203,"oceanRoom",1));
                roomDao.insert(new Room(204,"oceanRoom",1));
                roomDao.insert(new Room(205,"oceanRoom",1));
                roomDao.insert(new Room(208,"insideRoom",1));
                roomDao.insert(new Room(209,"insideRoom",1));
                roomDao.insert(new Room(210,"insideRoom",1));
                roomDao.insert(new Room(211,"insideRoom",1));
                roomDao.insert(new Room(212,"insideRoom",1));
                roomDao.insert(new Room(213,"insideRoom",1));
                roomDao.insert(new Room(214,"insideRoom",1));
                roomDao.insert(new Room(215,"insideRoom",1));
                roomDao.insert(new Room(216,"oceanRoom",1));
                roomDao.insert(new Room(217,"oceanRoom",1));
                roomDao.insert(new Room(218,"verandahRoom",1));
                roomDao.insert(new Room(219,"verandahRoom",1));
                roomDao.insert(new Room(220,"conciergeRoom",1));
//                roomDao.bookRoom(201,1);
                saDao.insert(new SpecialActivity(1,"KTV",true,true,1));
                saDao.insert(new SpecialActivity(2,"KTV",true,true,null));

                saDao.insert(new SpecialActivity(3,"Day 1",true,true,1));
                saDao.insert(new SpecialActivity(4,"Day 2",true,true,1));
                saDao.insert(new SpecialActivity(5,"Day 3",true,true,1));
                saDao.insert(new SpecialActivity(6,"Day 4",true,true,1));

                serviceDao.insert(new Service(1, "Morning Call",10.0));
                serviceDao.insert(new Service(2, "Food Delivery",10.3));
                roomServiceDao.insert(new RoomService(1,201));
//                reservationDao.insert(new Reservation(1,1));

                Log.d("DATABASE","AFTER INSERT DATA");
            });
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

//            Log.d("DATABASE","CREATED sRoomDatabaseCallback");
//
//            databaseWriteExecutor.execute(()->{
//                UsersDao dao = INSTANCE.usersDao();
//                dao.deleteAll();
//
//                User user = new User("John");
//                dao.insert(user);
//                user = new User("Wick");
//                dao.insert(user);
//
//                Log.d("DATABASE","AFTER INSERT DATA");
//            });
        }
    };
}
