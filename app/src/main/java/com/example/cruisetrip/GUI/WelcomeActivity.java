package com.example.cruisetrip.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruisetrip.R;
import com.example.cruisetrip.authentication.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new CountDownTimer(1000, 1000) {

            TextView textView = (TextView) findViewById(R.id.loading);

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
