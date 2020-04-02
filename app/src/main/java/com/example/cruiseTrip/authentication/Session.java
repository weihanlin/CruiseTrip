package com.example.cruiseTrip.authentication;

import android.content.Context;
import android.content.SharedPreferences;


public class Session {
    private SharedPreferences prefs;

    public Session (Context context) {
        prefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
    }

    public void setUserId(int userId) {
        prefs.edit().putInt("user_id", userId).commit();
    }

    public int getUserId() {
        return prefs.getInt("user_id",0);
    }

    public void setUsername(String username) {
        prefs.edit().putString("username", username).commit();
    }

    public String getUsername() {
        return prefs.getString("username","");
    }
}
