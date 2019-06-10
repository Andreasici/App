package com.example.app;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isValidLogin = true;
                String regexEmail = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
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

                    new AlertDialog.Builder(getActivity()).setTitle("Registrazione avvenuta con successo").setMessage("Registrazione avvenuta con successo").setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                    editConfirmPassword.setText("");
                }

            }

            // TODO complete with the btnRegistration action and redirect to another activity
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void openMapList(){
        // open the next activity
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
    }

}
