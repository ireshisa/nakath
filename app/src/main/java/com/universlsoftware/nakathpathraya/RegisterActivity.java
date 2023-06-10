package com.universlsoftware.nakathpathraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.universlsoftware.nakathpathraya.db.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper mysqllit;
    private EditText username, password,repassword;
    private TextView signupText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         mysqllit = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.siniButton);
        repassword= findViewById(R.id.repassword);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean validate = true;
                if (!password.getText().toString().equals(repassword.getText().toString()))
                {
                    repassword.setError("password Not matching");
                    password.setError("password Not matching");
                    validate = false;
                } else {
                    validate = true;
                }

                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Name Field is required");
                    validate = false;
                }

                if (validate == true) {
                    String Vpassword = password.getText().toString();
                    String Vusername = username.getText().toString();
                    boolean islog = mysqllit.insetData(Vusername, Vpassword);

                    if(true)
                    {
                        Toast.makeText(RegisterActivity.this, "User registered.", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else
                    {
                        Toast.makeText(RegisterActivity.this, "User is registration failed.", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "please Fill all  required Field  !.", Toast.LENGTH_LONG).show();
                }


            }
        });




    }
}