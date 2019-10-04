package com.example.routesplanner;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.routesplanner.activity.NewMapActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyMaps extends Fragment {

    Button createNewMapButton;

    public FragmentMyMaps() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_my_maps, container, false);
        //return inflater.inflate(R.layout.fragment_fragment_my_maps, container, false);

        createNewMapButton = (Button) view.findViewById(R.id.createNewMap);

        createNewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewMap(getView());
            }
        });

        return view;
    }

    // method called when the user wants to create a new map (using the button)
    public void createNewMap(View view){

        // call another activity and start create a new map
        Intent intent = new Intent(getActivity(), NewMapActivity.class);
        startActivity(intent);
    }

}