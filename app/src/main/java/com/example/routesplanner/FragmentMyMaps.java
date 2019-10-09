package com.example.routesplanner;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyMaps extends Fragment {

    TextView userEmail;
    Button createNewMapButton;
    Button logout;

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

        userEmail = (TextView) view.findViewById(R.id.txtUserEmail);

        createNewMapButton = (Button) view.findViewById(R.id.createNewMap);
        logout = (Button) view.findViewById(R.id.logout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        userEmail.setText(firebaseUser.getEmail());

        createNewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewMap(getView());
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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