package com.example.cruisetrip.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UsersRepository {
    private UsersDao usersDao;
    private RoomDao roomDao;
    private ReservationDao reservationDao;
    private RoomServiceDao roomServiceDao;
    private LiveData<List<User>> allUsers;

    public UsersRepository(Application application) {
        CruiseDatabase db = CruiseDatabase.getDatabase(application);
        roomDao = db.roomDao();
        usersDao = db.usersDao();
        reservationDao = db.reservationDao();
        roomServiceDao = db.roomServiceDao();
        allUsers = usersDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(final User user){
        CruiseDatabase.databaseWriteExecutor.execute(() -> {
            usersDao.insert(user);
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
