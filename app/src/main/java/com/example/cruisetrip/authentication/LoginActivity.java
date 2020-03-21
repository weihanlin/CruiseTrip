package com.example.cruisetrip.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruisetrip.GUI.DestinationActivity;
import com.example.cruisetrip.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
//    private ProgressBar loadingProgressBar;
    private TextView info;

    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        info = findViewById(R.id.infoTxt);
//        loadingProgressBar = findViewById(R.id.loading);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void validate (String username, String password) {

        if (username.equals("admin") && password.equals("1234")) {
            Intent i = new Intent(LoginActivity.this, DestinationActivity.class);
            startActivity(i);
        } else {
            counter--;

            info.setText( "Your username or password is not correct, please try again. \n" +
                    "No. of attempts remaining: " + String.valueOf(counter));

            if (counter == 0) {
                loginButton.setEnabled(false);
            }
        }
    }
}
