package com.example.routesplanner;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    EditText editEmail;
    EditText editPassword;
    Button btnLogin;

    FirebaseAuth firebaseAuth;


    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_login, container, false);

        // input's fields
        editEmail = (EditText) view.findViewById(R.id.editEmail);
        editPassword = (EditText) view.findViewById(R.id.editPassword);

        // login button
        btnLogin = (Button) view.findViewById(R.id.btnLogin);

        firebaseAuth = FirebaseAuth.getInstance();



        // if true it means that the user is already logged in
        if(getLoginStatus()){

            Intent intent = new Intent(getActivity(), MapsActivity.class);
            startActivity(intent);
        }

        // login button listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //Log.d("login status", "login before login "+getLoginStatus());
                if(checkLogin(editEmail, editPassword)) {

                    // data inserted valid, now check occurencies inside credential's db

                    firebaseAuth.signInWithEmailAndPassword(editEmail.getText().toString(),
                            editPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    // on complete login
                                    if (task.isSuccessful()) {
                                        // user exists inside Firebase Auth
                                        // now I save the sharedPreference property with the following method
                                        sharedUserLogin();

                                        Intent intent = new Intent(getActivity(), MapsActivity.class);
                                        startActivity(intent);
                                        Log.d("login status", "login after login "+getLoginStatus());
                                        getActivity().finish();
                                    } else {
                                        Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }else{
                    // data inserted wrong, error messages handled inside the method chedkLogin()
                }
            }
        });

        return view;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    private boolean checkLogin(EditText email, EditText password){
        Boolean isValidLogin = true; // isValidLogin
        String regexEmail = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        String pw = password.getText().toString();

        if(email.length() == 0 || !email.getText().toString().matches(regexEmail)) {
            email.setError("Email non valida");
            isValidLogin = false;
        }
        if(pw.length() < 8) {
            password.setError("Password non valida");
            isValidLogin = false;
        }

        return isValidLogin;
    }

    /**
     *
     */
    private void sharedUserLogin(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("saved_logged_status", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putBoolean("saved_logged_status", true);
        editor.commit();
    }

    private boolean getLoginStatus(){
        // calling the sharedPreferences in order to get the relative value
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("saved_logged_status", MODE_PRIVATE);
        // I store in the following var the value of the boolean corresponding to the key "R.string.saved_logged_status"
        boolean sharedUserLogged = sharedPreferences.getBoolean("saved_logged_status", false);
        return sharedUserLogged;
    }
}
