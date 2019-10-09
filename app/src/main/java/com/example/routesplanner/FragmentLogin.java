package com.example.routesplanner;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


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

        // login button listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.signInWithEmailAndPassword(editEmail.getText().toString(),
                                    editPassword.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                          // on complete login
                                            if(task.isSuccessful()){
                                                // user exists inside Firebase Auth
                                                Intent intent = new Intent(getActivity(), MapsActivity.class);
                                                startActivity(intent);
                                            }else{
                                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                /* check login
                if(checkLogin()){
                    // login ok
                    // intent to the next activity
                }else{
                    // login wrong
                    // notice user
                }*/
            }
        });

        return view;
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
