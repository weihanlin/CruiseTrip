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

//                reservationDao.deleteAll();
//                roomServiceDao.deleteAll();
//                serviceDao.deleteAll();
//                saDao.deleteAll();
//                roomDao.deleteAll();
//                dao.deleteAll();
//                itinerariesDao.deleteAll();

                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
                try {
                    itinerariesDao.insert(new Itinerary(1,"Alaska", fmt.parse("2020-05-20"), 5, "img_trip_alaska"));
                    itinerariesDao.insert(new Itinerary(2,"Europe", fmt.parse("2020-09-20"), 5, "img_trip_europe"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dao.insertUser("admin", "admin123", "1@gmail.com", "7886542234");
                dao.insertUser("another", "other123", "2@gmail.com", "7884587288");
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

                SpecialActivity onboardActivity = new SpecialActivity(1,"Amari Tasting",true,true,1);
                onboardActivity.setDescription("Delve into a diverse menu of Italian Amari drinks as the perfect primer to this complex class of herbal spirits.");
                try {
                    onboardActivity.setStart(fmt.parse("2020-05-21"));
                }catch (ParseException e){}
                saDao.insert(onboardActivity);

                onboardActivity.setId(2);
                onboardActivity.setTitle("Beer Tasting");
                onboardActivity.setDescription("Let’s make a toast to the world’s oldest alcoholic drink—from ales and lagers to stouts, wheats and beyond!");
                try {
                    onboardActivity.setStart(fmt.parse("2020-05-20"));
                }catch (ParseException e){}
                saDao.insert(onboardActivity);

                onboardActivity.setId(3);
                onboardActivity.setTitle("Chocolate & Liquor Tasting");
                onboardActivity.setDescription("Indulge in the exquisite pairings of rich chocolates with premium Champagne and liquors—c’est magnifique!");
                try {
                    onboardActivity.setStart(fmt.parse("2020-05-22"));
                }catch (ParseException e){}
                saDao.insert(onboardActivity);


                SpecialActivity days = new SpecialActivity(4,"Day 1",true,true,1);
                days.setDescription("<p><b>ATV Expedition</b><br/>" +
                        "Roam the land via an ATV while soaking up stunning valley, mountain and ocean views along the ride.</p>" +
                        "<p><b>Kayak Adventure</b><br/>" +
                        "Paddle a 2-person wilderness kayak accompanied by a safety-certified local guide along the Hoonah waterfront.</p>" +
                        "<p><b>Whales, Wildlife and Brown Bear Search</b><br/>" +
                        "Take a high-speed catamaran ride while searching for orca, sea lions, seals, humpback whales, bald eagles and other Alaskan creatures.</p>");
                try {
                    days.setStart(fmt.parse("2020-05-21"));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                saDao.insert(days);

                days.setId(5);
                days.setTitle("Day 2");
                days.setDescription("<p><b>Whale Park</b><br/>" +
                        "Enjoy beautiful seaside vistas as you look for majestic humpback whales, " +
                                "which can be spotted off the coast of Sitka in the summer.</p>" +
                        "<p><b>Fortress of the Bear</b><br/>" +
                        "Visit this habitat for orphaned brown bear cubs and view Alaska brown bears in a natural setting.</p>"
                        );
                try {
                    days.setStart(fmt.parse("2020-05-22"));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                saDao.insert(days);

                days.setId(6);
                days.setTitle("Day 3");
                days.setDescription("<p><b>Capilano Suspension Bridge</b><br/>" +
                        "Designed for walking use only, this 450-footlong bridge crosses a deep " +
                        "gorge of the Capilano River. Once across you will find a progressive series" +
                        " of platforms that snake along the primordial forest of British Columbia. " +
                        "Keep an eye out for birds and other wildlife that live in the woods surrounding the trails.</p>");
                try {
                    days.setStart(fmt.parse("2020-05-23"));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                saDao.insert(days);

                SpecialActivity portAdventure = new SpecialActivity(7,"Capilano Suspension Bridge",false, true, 1);
                portAdventure.setMaxNumber(10);
                portAdventure.setPrice(990);
                try {
                    portAdventure.setStart(fmt.parse("2020-05-23"));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                portAdventure.setDescription("Private Tour limit 10 people per group");
                saDao.insert(portAdventure);

                portAdventure.setId(8);
                portAdventure.setMaxNumber(0);
                portAdventure.setDescription("age 10 and above");
                portAdventure.setPrice(99);
                saDao.insert(portAdventure);

                portAdventure.setId(9);
                portAdventure.setMaxNumber(0);
                portAdventure.setDescription("age 3 to 9");
                portAdventure.setPrice(59);
                saDao.insert(portAdventure);


                portAdventure.setId(10);
                portAdventure.setTitle("Best of Sitka");
                portAdventure.setMaxNumber(10);
                portAdventure.setPrice(790);
                try {
                    portAdventure.setStart(fmt.parse("2020-05-21"));
                }
                catch (ParseException e) {
                    e.printStackTrace();
                }
                portAdventure.setDescription("Private Tour limit 10 people per group");
                saDao.insert(portAdventure);

                portAdventure.setId(11);
                portAdventure.setMaxNumber(0);
                portAdventure.setDescription("age 10 and above");
                portAdventure.setPrice(79);
                saDao.insert(portAdventure);

                portAdventure.setId(12);
                portAdventure.setMaxNumber(0);
                portAdventure.setDescription("age 3 to 9");
                portAdventure.setPrice(64);
                saDao.insert(portAdventure);


                serviceDao.insert(new Service(1, "Morning Call",0));
                serviceDao.insert(new Service(2, "Food Delivery",5));
                serviceDao.insert(new Service(3, "Chill Spa",70));
                serviceDao.insert(new Service(4, "Senses Spa & Salon",80));
                roomServiceDao.insert(new RoomService(1,201));
//                reservationDao.insert(new Reservation(1,1));

//                User user = new User("Joe","123","123@sss","7781234567");
//                user.setId(1);
//                dao.insert(user);

                Log.d("DATABASE","AFTER INSERT DATA");
            });
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };
}
