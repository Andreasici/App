package com.example.routesplanner.activity;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.routesplanner.CustomMapsAdapter;
import com.example.routesplanner.R;

public class MapsActivity extends AppCompatActivity {

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
    }
}
