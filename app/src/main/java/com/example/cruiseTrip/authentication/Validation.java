package com.example.cruiseTrip.authentication;

import android.app.Application;

import com.example.cruiseTrip.database.UsersRepository;
import com.example.cruiseTrip.database.entity.User;

import java.util.List;

public class Validation {
    private UsersRepository usersRepository;
    private Application application;
    private List<User> users;
    private boolean identifier;

    // Default cons
    public Validation(Application application) {
        this.application = application;
    }

    // Validate the username and password
    public boolean validate(String name, String password) {

        usersRepository = new UsersRepository(application);
        users = usersRepository.getAllUsers();
        identifier = false;
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                String userName = user.getName();
                String userPassword = user.getPassword();
                int userId = user.getId();
                if (name.equals(userName) && password.equals(userPassword)) {
                    identifier = true;
                    // Store the username and userId into the session
                    Session session = new Session(application.getApplicationContext());
                    session.setUsername(name);
                    session.setUserId(userId);
                }
            }
        }
        return identifier;
    }
}