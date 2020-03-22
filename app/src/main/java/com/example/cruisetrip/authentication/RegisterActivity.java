package com.example.cruisetrip.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruisetrip.R;
import com.example.cruisetrip.database.User;
import com.example.cruisetrip.database.UsersRepository;

import java.sql.SQLException;
import java.util.Base64;

public class RegisterActivity extends AppCompatActivity {
    private User newUser;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private String username;
    private String hashedPassword;
    private String email;
    private String phoneNumber;

    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.username_register);
        passwordEditText = findViewById(R.id.password_register);
        emailEditText = findViewById(R.id.email_register);
        phoneNumberEditText = findViewById(R.id.phone_register);

        this.username = usernameEditText.getText().toString();
        this.hashedPassword = Base64.getEncoder().encodeToString(passwordEditText.getText().toString().getBytes());
        this.email = emailEditText.getText().toString();
        this.phoneNumber = phoneNumberEditText.getText().toString();

        try {
            UsersRepository usersRepository = new UsersRepository(this.getApplication());
            newUser = new User(username, hashedPassword, email, phoneNumber);
            usersRepository.insert(newUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        signUpBtn = findViewById(R.id.signUpBtn_register);
        signUpBtn.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            i.putExtra("registerStatus", true);
            startActivity(i);
        });
    }

    //Getters and setters
    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
