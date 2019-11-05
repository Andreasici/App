package com.example.routesplanner;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyMaps extends Fragment {

    TextView userEmail;
    Button createNewMapButton;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    public FragmentMyMaps() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_my_maps, container, false);
        //return inflater.inflate(R.layout.fragment_fragment_my_maps, container, false);

        //userEmail = (TextView) view.findViewById(R.id.txtUserEmail);

        createNewMapButton = (Button) view.findViewById(R.id.createNewMap);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        //userEmail.setText(firebaseUser.getEmail());

        createNewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewMap(getView());
            }
        });

        //vdfvbklsdbvdbsv
        createNewMapButton.setText("Nuova Mappa");

        return view;
    }

    // method called when the user wants to create a new map (using the button)
    public void createNewMap(View view){

        // call another activity and start create a new map
        Intent intent = new Intent(getActivity(), NewMapActivity.class);
        startActivity(intent);
    }

}