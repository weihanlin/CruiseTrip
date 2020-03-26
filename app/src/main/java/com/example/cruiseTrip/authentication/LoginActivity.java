package com.example.cruiseTrip.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.ui.DestinationActivity;
import com.example.cruiseTrip.R;
import com.example.cruiseTrip.entity.User;
import com.example.cruiseTrip.database.UsersRepository;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private Button jumpBtn;
    private TextView info;
    private TextView title;
    private UsersRepository usersRepository;

    private int counter = 5;
    private List<User> usersList;

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
            // Validating
            validate(usernameEditText.getText().toString(), passwordEditText.getText().toString());
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

    // Validate the username and password
    private void validate (String name, String password) {

        usersRepository = new UsersRepository(this.getApplication());
        usersRepository.getAllUsers().observe(this, userList -> {
            Boolean identifier = false;
            if(usersList != null && !usersList.isEmpty()) {
                for (User user : usersList) {
                    String userName = user.getName();
                    String userPassword = user.getPassword();
                    if (name.equals(userName) && password.equals(userPassword))
                        identifier = true;
                }
                // True : jump to the destination page.
                // False : attempts - 1. When attempts = 0, the log in button will disable
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
            }
        });
    }
}