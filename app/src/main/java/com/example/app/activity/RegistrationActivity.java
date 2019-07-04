package com.example.app.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.R;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editSurname;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editConfirmPassword;
    private Button btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        btnRegistration = findViewById(R.id.btnRegistration);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValidLogin = true;
                String regexEmail = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
                String password = editPassword.getText().toString();
                String passwordConfirm = editConfirmPassword.getText().toString();
                if(editName.length() == 0) {
                    editName.setError("Inserire un nome");
                    isValidLogin = false;
                }
                if(editSurname.length() == 0) {
                    editSurname.setError("Inserire un cognome");
                    isValidLogin = false;
                }
                if(editEmail.length() == 0 || !editEmail.getText().toString().matches(regexEmail)) {
                    editEmail.setError("Email non corretta");
                    isValidLogin = false;
                }
                if(editPassword.length() < 8) {
                    if(editPassword.length() == 0 && editConfirmPassword.length() == 0) {
                        editPassword.setError("Password non inserita");
                        editConfirmPassword.setError("Password non inserita");
                    }

                    else
                        editPassword.setError("Password troppo corta");
                    isValidLogin = false;
                }
                if(!password.equals(passwordConfirm)) {
                    editPassword.setError("Le Password non coincidono");
                    editConfirmPassword.setError("Le Password non coincidono");
                    isValidLogin = false;
                }

                if(isValidLogin) {

                    new AlertDialog.Builder(RegistrationActivity.this).setTitle("Registrazione avvenuto con successo").setMessage("Registrazione avvenuto con successo").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                    editName.setText("");
                    editSurname.setText("");
                    editEmail.setText("");
                    editPassword.setText("");
                    editConfirmPassword.setText("");
                }

            }

            // TODO complete with the btnRegistration action and redirect to another activity
        });




    }
}
