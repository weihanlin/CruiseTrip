package com.example.cruisetrip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new CountDownTimer(4000, 1000) {

            TextView textView = (TextView) findViewById(R.id.loading);

            public void onTick(long millisUntilFinished) {
                textView.setText("Please Wait: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent i = new Intent(WelcomeActivity.this, DestinationActivity.class);
                startActivity(i);
            }
        }.start();
    }
}
