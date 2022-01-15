package com.milewczyk.wsb_51033_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView registerQuestion;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLoginButton();
        initRegisterInformation();
    }

    private void initLoginButton() {
        loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
    }



    private void initRegisterInformation() {
        registerQuestion = findViewById(R.id.Register1Main);
        registerQuestion.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));

        register = findViewById(R.id.Register2Main);
        register.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }
}