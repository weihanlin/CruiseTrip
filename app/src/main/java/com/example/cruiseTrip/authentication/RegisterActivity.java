package com.example.cruiseTrip.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cruiseTrip.R;
import com.example.cruiseTrip.database.entity.User;
import com.example.cruiseTrip.database.UsersRepository;

public class RegisterActivity extends AppCompatActivity {
    private User newUser;

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordConformEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private TextView errorsTxtView;
    private String username;
    private String password;
    private String passwordConform;
    private String email;
    private String phoneNumber;

    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.username_register);
        passwordEditText = findViewById(R.id.password_register);
        passwordConformEditText = findViewById(R.id.password_confirm_register);
        emailEditText = findViewById(R.id.email_register);
        phoneNumberEditText = findViewById(R.id.phone_register);

        signUpBtn = findViewById(R.id.signUpBtn_register);
        signUpBtn.setOnClickListener(v -> {
            username = usernameEditText.getText().toString().trim();
            password = passwordEditText.getText().toString().trim();
            passwordConform = passwordConformEditText.getText().toString().trim();
            email = emailEditText.getText().toString().trim();
            phoneNumber = phoneNumberEditText.getText().toString().trim();
            errorsTxtView = findViewById(R.id.errors_register);
            errorsTxtView.setText("");

            //Validate
            Validation validation = new Validation(username, password, passwordConform, email, phoneNumber);
            if(validation.validateForm().equals("")) {
                try {
                    UsersRepository usersRepository = new UsersRepository(this.getApplication());
                    newUser = new User(username, password, email, phoneNumber);
                    usersRepository.insertUser(newUser);
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    i.putExtra("registerStatus", true);
                    startActivity(i);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                String s = validation.validateForm();
                errorsTxtView.setText(s);
                passwordEditText.setText("");
                passwordConformEditText.setText("");
            }
        });
    }
}
