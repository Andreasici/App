package com.example.routesplanner;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRegistration extends Fragment {
    private EditText editName;
    private EditText editSurname;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editConfirmPassword;
    private Button btnRegistration;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FragmentRegistration() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_registration, container, false);

        editName = (EditText) view.findViewById(R.id.editName);
        editSurname = (EditText) view.findViewById(R.id.editSurname);
        editEmail = (EditText) view.findViewById(R.id.editEmail);
        editPassword = (EditText) view.findViewById(R.id.editPassword);
        editConfirmPassword = (EditText) view.findViewById(R.id.editConfirmPassword);
        btnRegistration = (Button) view.findViewById(R.id.btnRegistration);

        firebaseAuth = FirebaseAuth.getInstance();

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValidLogin = true; // isValidLogin
                final String regexEmail = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
                String password = editPassword.getText().toString();
                String passwordConfirm = editConfirmPassword.getText().toString();
                if(editName.length() == 0) {
                    editName.setError("Inserire un nome");
                    isValidLogin = false;
                }
                if(editSurname.length() == 0) {
                    editSurname.setError("Inserire un cognome");
                    isValidLogin = false;
                }
                if(editEmail.length() == 0 || !editEmail.getText().toString().matches(regexEmail)) {
                    editEmail.setError("Email non corretta");
                    isValidLogin = false;
                }
                if(editPassword.length() < 8) {
                    if(editPassword.length() == 0 && editConfirmPassword.length() == 0) {
                        editPassword.setError("Password non inserita");
                        editConfirmPassword.setError("Password non inserita");
                    }

                    else
                        editPassword.setError("Password troppo corta");
                    isValidLogin = false;
                }
                if(!password.equals(passwordConfirm)) {
                    editPassword.setError("Le Password non coincidono");
                    editConfirmPassword.setError("Le Password non coincidono");
                    isValidLogin = false;
                }

                if(isValidLogin) {

                    firebaseAuth.createUserWithEmailAndPassword(editEmail.getText().toString(),
                            password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        // second usage -> FirebaseFireStore to add new record
                                        // initialize new HashMap to contain the user info
                                        Map<String, Object> user = new HashMap<>();
                                        // setting the values
                                        user.put("name", editName.getText().toString());
                                        user.put("surnae", editSurname.getText().toString());
                                        user.put("email", editEmail.getText().toString());

                                        // Add a new document with a generated ID
                                        db.collection("users")
                                                .add(user)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.d("firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
                                                        new AlertDialog.Builder(getActivity()).setTitle("Registrazione avvenuta con successo").setMessage("Registrazione avvenuta con successo").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                sharedUserLogin();
                                                                openMapList();
                                                            }
                                                        }).setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {

                                                            }
                                                        }).show();
                                                        editName.setText("");
                                                        editSurname.setText("");
                                                        editEmail.setText("");
                                                        editPassword.setText("");
                                                        editConfirmPassword.setText("");

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.d("firestore", "Error adding document", e);
                                                    }
                                                });


                                    }else{
                                        Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                    }

                    /*new AlertDialog.Builder(getActivity()).setTitle("Registrazione avvenuta con successo").setMessage("Registrazione avvenuta con successo").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            openMapList();
                        }
                    }).setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                    editName.setText("");
                    editSurname.setText("");
                    editEmail.setText("");
                    editPassword.setText("");
                    editConfirmPassword.setText("");*/
                /*}*/

            }

        });
        // Inflate the layout for this fragment
        return view;
    }

    private void openMapList(){
        // open the next activity
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }

    /**
     *
     */
    private void sharedUserLogin(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("saved_logged_status", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putBoolean("saved_logged_status", true);
        editor.commit();
    }

}
