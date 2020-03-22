package com.example.cruisetrip.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.cruisetrip.GUI.DestinationActivity;
import com.example.cruisetrip.R;
import com.example.cruisetrip.database.User;
import com.example.cruisetrip.database.UsersRepository;

import java.util.Base64;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private TextView info;
    private TextView title;
    private UsersRepository usersRepository;

    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username_login);
        passwordEditText = findViewById(R.id.password_login);
        loginButton = findViewById(R.id.loginBtn);
        registerButton = findViewById(R.id.registerBtn);
        info = findViewById(R.id.infoTxt_login);
        title = findViewById(R.id.title_login);

        if(getIntent().getBooleanExtra("registerStatus", false)) {
            title.setText("Register Success! \n" +
                    "Please log in!");
        }

        loginButton.setOnClickListener(v -> {
            // Validating
            validate(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        });

        registerButton.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(i);
        });
    }

    // Validate the username and password
    private void validate (String name, String password) {

        String hashedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        Boolean identifier = false;

        usersRepository = new UsersRepository(this.getApplication());
        LiveData<List<User>> users = usersRepository.getAllUsers();
        List<User> listUsers = users.getValue();
        for(User user : listUsers) {
            String userName = user.getName();
            String userPassword = user.getPassword();
            if (name.equals(userName) && hashedPassword.equals(userPassword)) {
                identifier = true;
            }
        }

        // True : jump to the destination page.
        // False : attempts - 1. When attempts = 0, the log in button will disable
        if (identifier) {
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