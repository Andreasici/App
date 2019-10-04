package com.example.routesplanner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CustomPageAdapter extends FragmentStatePagerAdapter {

    public CustomPageAdapter(FragmentManager fm) {super(fm);}

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = null;

        switch (i) {
            case 0:
                fragment = new FragmentLogin();
                break;
            case 1:
                fragment = new FragmentRegistration();
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
                return "Login";
            case 1:
                return "Registrazione";
            default:
                return null;
        }
    }
}