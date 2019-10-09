package com.example.routesplanner;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeClass extends Application {

    public void onCreate(){
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent(this, MapsActivity.class));
        }/*else{
            startActivity(new Intent(this, MainActivity.class));
        }*/
    }
}
