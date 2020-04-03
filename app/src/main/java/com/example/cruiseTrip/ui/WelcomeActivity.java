package com.example.cruiseTrip.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.authentication.LoginActivity;
import com.example.cruiseTrip.authentication.Session;
import com.example.cruiseTrip.database.entity.RoomService;
import com.example.cruiseTrip.roomBooking.RoomServiceActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Session session = new Session(this);
        session.setUsername("");
        session.setUserId(0);
        session.setPrice(0);

        new CountDownTimer(3000, 1000) {

            TextView textView = findViewById(R.id.loading);

            public void onTick(long millisUntilFinished) {
                textView.setText("Loading... Please Wait: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }.start();
    }
}
