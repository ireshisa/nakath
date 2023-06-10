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

public class EditActivity extends AppCompatActivity {


    DatabaseHelper mysqllit;
    private EditText username, password,repassword;
    private TextView signupText;
    private Button loginButton,deletebutton,edit;


String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mysqllit = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        edit = findViewById(R.id.siniButton);
        deletebutton = findViewById(R.id.deletebutton);


        MySharedPreferences sharedPreferences = new MySharedPreferences(getApplicationContext());
        userid=sharedPreferences.getid();

        repassword= findViewById(R.id.repassword);
        loradData(userid);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer islog = mysqllit.deletData(userid);
                MySharedPreferences sharedPreferences = new MySharedPreferences(getApplicationContext());
                sharedPreferences.clear();
                startActivity(new Intent(EditActivity.this, LoginActivity.class));

            }
        });




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean validate = true;
                if (!password.getText().toString().equals(repassword.getText().toString())) {
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
                    boolean islog = mysqllit.updatedata(userid, Vusername,Vpassword);

                    if(true)
                    {
                        Toast.makeText(EditActivity.this, "Update is Success.", Toast.LENGTH_LONG).show();;
                    }else
                    {
                        Toast.makeText(EditActivity.this, "User is registration failed.", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(EditActivity.this, "please Fill all  required Field  !.", Toast.LENGTH_LONG).show();
                }

                loradData(userid);
            }
        });


    }

    private void loradData(String userId ) {

   String[] userData = mysqllit.getUserData(userId);
        if (userData != null) {
            String Mname = userData[0];
            String Mpassword = userData[1];
            // display the user's name and password as needed
            // for example, you can set the text of a TextView to the user's name
            username.setText(Mname);
            password.setText(Mpassword);
            repassword.setText(Mpassword);
        }

    }
}