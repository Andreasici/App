package com.example.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnRegistration;
    private Button btnGoogleLogin;
    private Button btnFacebookLogin;
    private Button btnNormalLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFacebookLogin = findViewById(R.id.btnFacebookLogin);
        btnRegistration = findViewById(R.id.btnRegistration);
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin);
        btnNormalLogin = findViewById(R.id.btnNormalLogin);


        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegistration();
            }
        });

        btnNormalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNormalLogin();
            }
        });
    }

    private void openRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
    private void openNormalLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
