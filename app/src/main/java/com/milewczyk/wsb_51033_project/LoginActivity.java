package com.milewczyk.wsb_51033_project;

import static android.text.TextUtils.*;
import static com.milewczyk.wsb_51033_project.ValidationService.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageView backButton;
    TextView registerQuestion;
    TextView register;
    Button loginButton;

    EditText emailInput;
    EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initRegisterInformation();
        initLoginButton();
        initBackButton();
        initInputFields();
    }

    private void initBackButton() {
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());
    }

    private void initInputFields() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        if (getIntent().getExtras() != null) {
            emailInput.setText(getIntent().getExtras().getString("email"));
            passwordInput.setText(getIntent().getExtras().getString("password"));
        }
    }

    private void initLoginButton() {
        loginButton = findViewById(R.id.loginActivityButton);
        loginButton.setOnClickListener(view -> validateInputsAndGetResult(
                emailInput.getText().toString(), passwordInput.getText().toString()));
    }

    private void validateInputsAndGetResult(String email, String password) {
        if (!isEmpty(email) && validateEmail(email) && !isEmpty(password) && validatePassword(password)) {
            loginButton.setOnClickListener(view -> startActivity(
                    new Intent(LoginActivity.this, SuccessfulLoginActivity.class)));
        } else {
            if (!validateEmail(email)) {
                Toast.makeText(getApplicationContext(),"Niepoprawny adres e-mail!",Toast.LENGTH_SHORT).show();
            }
            if (!validatePassword(password)) {
                Toast.makeText(getApplicationContext(),"Niepoprawne hasło!",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initRegisterInformation() {
        registerQuestion = findViewById(R.id.Register1Login);
        registerQuestion.setOnClickListener(view -> startActivity(
                new Intent(LoginActivity.this, RegisterActivity.class)));

        register = findViewById(R.id.Register2Login);
        register.setOnClickListener(view -> startActivity(
                new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}