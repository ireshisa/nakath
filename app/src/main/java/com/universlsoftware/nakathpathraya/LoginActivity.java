package com.universlsoftware.nakathpathraya;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.universlsoftware.nakathpathraya.db.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper mysqllit;
    private EditText username, password;
    private TextView signupText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mysqllit = new DatabaseHelper(this);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean validate = true;
                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Name Field is required");
                    validate = false;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Name Field is required");
                    validate = false;
                }


                if (validate == true) {
                    String Vpassword = password.getText().toString();
                    String Vusername = username.getText().toString();
                    int islog = mysqllit.login(Vusername, Vpassword);
                    if (islog != 0) {
                        Toast.makeText(LoginActivity.this, "Suscess", Toast.LENGTH_SHORT).show();

                        MySharedPreferences sharedPreferences = new MySharedPreferences(getApplicationContext());
                        sharedPreferences.setLoggedIn(true);
                        sharedPreferences.setUser(Vusername, String.valueOf(islog));
                        startActivity(new Intent(LoginActivity.this, OnbordActivity.class));

                    } else {
                        Toast.makeText(LoginActivity.this, "Check Login Details  ...!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "please Fill all  required Field  ", Toast.LENGTH_LONG).show();
                }

            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        useralradylog();
    }

    private void useralradylog() {

        MySharedPreferences sharedPreferences = new MySharedPreferences(getApplicationContext());
        if (sharedPreferences.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }


    }

}