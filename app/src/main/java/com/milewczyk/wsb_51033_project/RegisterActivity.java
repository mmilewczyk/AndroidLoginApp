package com.milewczyk.wsb_51033_project;

import static android.text.TextUtils.isEmpty;
import static com.milewczyk.wsb_51033_project.ValidationService.validateEmail;
import static com.milewczyk.wsb_51033_project.ValidationService.validatePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    ImageView backButton;
    TextView loginQuestion;
    TextView login;
    Button registerButton;

    EditText emailInput;
    EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initBackButton();
        initInputFields();
        initRegisterButton();
        initLoginInformation();
    }

    private void initBackButton() {
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());
    }

    private void initInputFields() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
    }

    private void initRegisterButton() {
        registerButton = findViewById(R.id.registerActivityButton);
        registerButton.setOnClickListener(view -> {
            if(validateInputsAndGetResult(emailInput.getText().toString(), passwordInput.getText().toString())) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra("email", emailInput.getText().toString());
                intent.putExtra("password", passwordInput.getText().toString());
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });
    }

    private boolean validateInputsAndGetResult(String email, String password) {
        if (!isEmpty(email) && validateEmail(email) && !isEmpty(password) && validatePassword(password)) {
            return true;
        } else {
            if (!validateEmail(email)) {
                Toast.makeText(getApplicationContext(),"Niepoprawny adres e-mail!",Toast.LENGTH_SHORT).show();
            }
            if (!validatePassword(password)) {
                Toast.makeText(getApplicationContext(),"Niepoprawne hasło!",Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    private void initLoginInformation() {
        loginQuestion = findViewById(R.id.Login1);
        loginQuestion.setOnClickListener(view -> startActivity(
                new Intent(RegisterActivity.this, LoginActivity.class)));

        login = findViewById(R.id.Login2);
        login.setOnClickListener(view -> startActivity(
                new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}