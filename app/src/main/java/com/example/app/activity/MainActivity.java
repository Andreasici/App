package com.example.app.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.app.CustomPageAdapter;
import com.example.app.R;

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
    }
}