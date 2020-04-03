package com.example.cruiseTrip.authentication;

import android.app.Application;

import com.example.cruiseTrip.database.UsersRepository;
import com.example.cruiseTrip.database.entity.User;

import java.util.List;
import java.util.regex.Pattern;

public class Validation {
    private UsersRepository usersRepository;
    private Application application;
    private List<User> users;
    private boolean identifier;
    private String registerUsername;
    private String registerPassword;
    private String registerPasswordConform;
    private String registerEmail;
    private String registerPhoneNumber;

    public Validation(String username, String password, String passwordConform, String email, String phoneNumber) {
        this.registerUsername = username;
        this.registerPassword = password;
        this.registerPasswordConform = passwordConform;
        this.registerEmail = email;
        this.registerPhoneNumber = phoneNumber;
    }

    public Validation(Application application) {
        this.application = application;
    }

    // Validate the username and password
    public boolean validateLogin(String name, String password) {
        try {
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return identifier;
    }

    public String validateForm() {
        String s = "";
        String passwordRegex = "^(?=.*[0-9]).{8,}$";
        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Pattern emailPattern = Pattern.compile(emailRegex);
        if(registerUsername.equals("") || registerPassword.equals("") || registerPasswordConform.equals("") || registerEmail.equals("")) {
            s = "Please fill in the required field.\n";
        } else {
            if (!registerPassword.equals(registerPasswordConform)) {
                s += "Your two passwords entered should be the same!\n";
            } else if (!passwordPattern.matcher(registerPassword).matches()){
                s += "Your password must contain at least 8 characters and 1 number!\n";
            }
            if (!emailPattern.matcher(registerEmail).matches()){
                s += "Please enter the correct email address.\n";
            }
        }
        return s.trim();
    }
}