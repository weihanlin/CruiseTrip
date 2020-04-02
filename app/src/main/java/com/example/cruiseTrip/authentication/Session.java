package com.example.cruiseTrip.authentication;

import android.content.Context;
import android.content.SharedPreferences;


public class Session {
    private SharedPreferences prefs;

    public Session (Context context) {
        prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        prefs.edit().putString("username", username).commit();
    }

    public String getUsername() {
        String username = prefs.getString("username","");
        return username;
    }
}
