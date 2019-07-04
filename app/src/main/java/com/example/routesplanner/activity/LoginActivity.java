package com.example.routesplanner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.routesplanner.R;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // input's fields
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        // login button
        btnLogin = findViewById(R.id.btnLogin);

        // login button listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check login
                if(checkLogin()){
                    // login ok
                    // intent to the next activity
                }else{
                    // login wrong
                    // notice user
                }
            }
        });
    }

    /*
        Check here the login info connecting to the web service
     */
    private boolean checkLogin(){
        /* TODO
        // 1) connecting to the web service as database for account check (email/password)
        // 2) check whether the input's values match some occurencies on the database
        // 3a) if match -> open next Activity, save it
        // 3b) if not match -> notice user about errors
        */
        return true;
    }
}
