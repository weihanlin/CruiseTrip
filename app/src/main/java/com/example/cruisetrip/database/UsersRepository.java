package com.example.cruisetrip.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UsersRepository {
    private UsersDao usersDao;
    private LiveData<List<User>> allUsers;

    public UsersRepository(Application application) {
        CruiseDatabase db = CruiseDatabase.getDatabase(application);
        usersDao = db.usersDao();
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
}
