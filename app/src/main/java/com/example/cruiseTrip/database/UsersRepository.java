package com.example.cruiseTrip.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cruiseTrip.database.entity.Reservation;
import com.example.cruiseTrip.database.entity.Room;
import com.example.cruiseTrip.database.entity.RoomService;
import com.example.cruiseTrip.database.entity.User;

import java.util.List;

public class UsersRepository {
    private UsersDao usersDao;
    private RoomDao roomDao;
    private ReservationDao reservationDao;
    private RoomServiceDao roomServiceDao;
    private CruiseDatabase db;

    public UsersRepository(Application application) {
        this.db = CruiseDatabase.getDatabase(application);
        this.roomDao = db.roomDao();
        this.reservationDao = db.reservationDao();
        this.roomServiceDao = db.roomServiceDao();
        this.usersDao = db.usersDao();
    }

    public List<User> getAllUsers() {
        this.usersDao = db.usersDao();
        return usersDao.getAllUsers();
    }

    public void insertUser(User user){
        CruiseDatabase.databaseWriteExecutor.execute(()->{
            String name = user.getName();
            String password = user.getPassword();
            String email = user.getEmail();
            String phone = user.getPhone();
            this.usersDao.insertUser(name, password, email, phone);
        });
    }

    public User getUser(String username) {
        this.usersDao = db.usersDao();
        return usersDao.getUser(username);
    }

    //Query reservations for specific user
    public LiveData<List<Reservation>> getReservationByUser(User user){
        this.reservationDao = db.reservationDao();
        return reservationDao.getReservationByUserID(user.getId());
    }

    //Query rooms for specific user
    public LiveData<List<Room>> getBookedRooms(User user) {
        this.roomDao = db.roomDao();
        return roomDao.getBookedRooms(user.getId());
    }

    //Query room_services record of specific room
    public LiveData<List<RoomService>> getRoomServiceRecord(Room room){
        this.roomServiceDao = db.roomServiceDao();
        return roomServiceDao.getRecordOfRoom(room.getId());
    }
}
