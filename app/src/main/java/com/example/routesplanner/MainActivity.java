package com.example.routesplanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabs;
    private CustomPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanzio il ViewPager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);

        //Instanzio l'adapter
        adapter = new CustomPageAdapter(getSupportFragmentManager());

        //Assegno l'adapter al ViewPager
        viewPager.setAdapter(adapter);

        //Istanzio il TabLayout
        tabs = findViewById(R.id.tabs);

        //Associo il TabLayout al ViewPager
        tabs.setupWithViewPager(viewPager);

        // calling the sharedPreferences in order to get the relative value
        //SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        // I store in the following var the value of the boolean corresponding to the key "R.string.saved_logged_status"
        //boolean sharedUserLogged = sharedPreferences.getBoolean(getString(R.string.saved_logged_status), true);

//        if(sharedUserLogged){ //wronggggggggggg
 //           Intent intent = new Intent(this,  MapsActivity.class);
   //         startActivity(intent);
     //   }else{
            // User not logged yet
       // }
    }
}