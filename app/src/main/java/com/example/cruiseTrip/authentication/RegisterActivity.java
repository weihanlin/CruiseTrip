package com.example.cruiseTrip.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.User;
import com.example.cruiseTrip.database.UsersRepository;

public class RegisterActivity extends AppCompatActivity {
    private User newUser;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private String username;
    private String password;
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

        signUpBtn = findViewById(R.id.signUpBtn_register);
        signUpBtn.setOnClickListener(v -> {
            this.username = usernameEditText.getText().toString();
            this.password = passwordEditText.getText().toString();
            this.email = emailEditText.getText().toString();
            this.phoneNumber = phoneNumberEditText.getText().toString();

            try {
                UsersRepository usersRepository = new UsersRepository(this.getApplication());
                newUser = new User(this.username, this.password, this.email, this.phoneNumber);
                usersRepository.insert(newUser);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            i.putExtra("registerStatus", true);
            startActivity(i);
        });
    }
}
