package com.example.cruiseTrip.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.ui.DestinationActivity;
import com.example.cruiseTrip.R;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private Button jumpBtn;
    private TextView info;
    private TextView title;

    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_login);
        passwordEditText = findViewById(R.id.password_login);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.registerBtn);
        jumpBtn = findViewById(R.id.jumpBtn);
        info = findViewById(R.id.infoTxt_login);
        title = findViewById(R.id.title_login);

        if(getIntent().getBooleanExtra("registerStatus", false)) {
            title.setText(getString(R.string.Register_success) +
                    getString(R.string.Login_msg));
        }

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Validating
            Validation validation = new Validation(getApplication());
            boolean identifier = validation.validateLogin(username, password);

            // True : jump to the destination page.
            // False : attempts - 1. When attempts = 0, the log in button will be disabled
            if (identifier) {
                Intent i = new Intent(LoginActivity.this, DestinationActivity.class);
                startActivity(i);
            } else {
                counter--;
                info.setText( "Your username or password is not correct, please try again. \n" +
                        "No. of attempts remaining: " + counter);
                if (counter == 0) {
                    loginButton.setEnabled(false);
                }
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        jumpBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, DestinationActivity.class);
            startActivity(i);
        });
    }
}