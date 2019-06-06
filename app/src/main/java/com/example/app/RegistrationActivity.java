package com.example.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editCognome;
    private EditText editTelefono;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editConfermaPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}
