package com.example.cruisetrip.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Itinerary.class, SpecialActivity.class,
        Room.class, Reservation.class, Service.class, RoomService.class},
        version = 3, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class CruiseDatabase extends RoomDatabase {
    public abstract UsersDao usersDao();
    public abstract ItinerariesDao itinerariesDao();
    public abstract RoomDao roomDao();
    public abstract SADao saDao();

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
                            .addCallback(sRoomDatabaseCallback)
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

                saDao.deleteAll();
                roomDao.deleteAll();
                dao.deleteAll();
                itinerariesDao.deleteAll();

                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
                try {

                    itinerariesDao.insert(new Itinerary(1,"Tokyo", fmt.parse("2020-05-20"), 5));
                    itinerariesDao.insert(new Itinerary(2,"Canada", fmt.parse("2020-09-20"), 5));

                } catch (ParseException e) {}

                dao.insert(new User(1,"John"));
                dao.insert(new User(2,"Wick"));
                roomDao.insert(new Room(1,"Sea",2));
                roomDao.insert(new Room(2,"Sea",1));
                roomDao.insert(new Room(3,"Sea",1));
                roomDao.bookRoom(1,2);
                saDao.insert(new SpecialActivity(1,"KTV",true,true,1));
                saDao.insert(new SpecialActivity(2,"KTV",true,true,null));



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
