package com.example.cruisetrip.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UsersRepository {
    private UsersDao usersDao;
    private RoomDao roomDao;
    private ReservationDao reservationDao;
    private RoomServiceDao roomServiceDao;
    private LiveData<List<User>> allUsers;
    private CruiseDatabase db;

    public UsersRepository(Application application) {
        this.db = CruiseDatabase.getDatabase(application);
//        this.roomDao = db.roomDao();
//        this.reservationDao = db.reservationDao();
//        this.roomServiceDao = db.roomServiceDao();
//        this.allUsers = usersDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        this.usersDao = db.usersDao();
        CruiseDatabase.databaseWriteExecutor.execute(()->{
            String name = user.getName();
            String password = user.getPassword();
            String email = user.getEmail();
            String phone = user.getPhone();
            this.usersDao.insert(name, password, email, phone);
        });
    }

    //Query reservations for specific user
    public LiveData<List<Reservation>> getReservationByUser(User user){
        return reservationDao.getReservationByUserID(user.getId());
    }

    //Query rooms for specific user
    public LiveData<List<Room>> getBookedRooms(User user) {
        return roomDao.getBookedRooms(user.getId());
    }

    //Query room_services record of specific room
    public LiveData<List<RoomService>> getRoomServiceRecord(Room room){
        return roomServiceDao.getRecordOfRoom(room.getId());
    }
}
