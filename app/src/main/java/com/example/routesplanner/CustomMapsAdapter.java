package com.example.routesplanner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomMapsAdapter extends FragmentStatePagerAdapter {

    public CustomMapsAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = null;

        switch (i) {
            case 0:
                fragment = new FragmentMyMaps();
                break;
            case 1:
                fragment = new FragmentComMaps();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Le mie mappe";
            case 1:
                return "Le mappe della community";
            default:
                return null;
        }
    }
}