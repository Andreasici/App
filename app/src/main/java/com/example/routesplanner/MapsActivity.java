package com.example.routesplanner;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MapsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;
    private ViewPager viewPager;
    private TabLayout tabs;
    private CustomMapsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Instanzio il ViewPager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);

        //Instanzio l'adapter
        adapter = new CustomMapsAdapter(getSupportFragmentManager());

        //Assegno l'adapter al ViewPager
        viewPager.setAdapter(adapter);

        //Istanzio il TabLayout
        tabs = findViewById(R.id.tabs);

        //Associo il TabLayout al ViewPager
        tabs.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_account:
                openAccountActivity();
                break;
            case R.id.nav_settings:
                openSettingsActivity();
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void openAccountActivity(){
        // open AccountActivity
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }
    private void openSettingsActivity(){
        // open SettingsActivity
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        // setting the sharedPreferences about logged-status to false
        SharedPreferences sharedPreferences = this.getSharedPreferences("saved_logged_status", MODE_PRIVATE);
        Log.d("login status", "before logout "+sharedPreferences.getBoolean("saved_logged_status", true));
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putBoolean("saved_logged_status", false);
        editor.commit();
        // intent
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.finish();

        // calling the mainActivity
        startActivity(intent);
        Log.d("login status", "after logout "+sharedPreferences.getBoolean("saved_logged_status", true));
    }
    private boolean getLoginStatus(){
        // calling the sharedPreferences in order to get the relative value
        SharedPreferences sharedPreferences = this.getSharedPreferences("saved_logged_status", MODE_PRIVATE);
        // I store in the following var the value of the boolean corresponding to the key "R.string.saved_logged_status"
        boolean sharedUserLogged = sharedPreferences.getBoolean("saved_logged_status", false);

        return sharedUserLogged;
    }
}
