package com.mirea.shiyanevskayaag.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment {

    EditText editTextName, editTextEmail;
    SharedPreferences sharedPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextName = rootView.findViewById(R.id.editTextName);
        editTextEmail = rootView.findViewById(R.id.editTextEmail);
        Button buttonSave = rootView.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        Context context = getActivity();
        sharedPref = context.getSharedPreferences("profile_settings", Context.MODE_PRIVATE);

        String nameSave = sharedPref.getString("NAME ", "unknown");
        String emailSave = sharedPref.getString("EMAIL ", "unknown");

        if(!nameSave.equals("unknown"))
        {
            editTextName.setText(nameSave);
        }
        if(!emailSave.equals("unknown"))
        {
            editTextEmail.setText(emailSave);
        }

        return rootView;
    }

    public void saveProfile()
    {
        SharedPreferences.Editor editor	= sharedPref.edit();

        String name = editTextName.getText().toString();
        editor.putString("NAME", name);

        String email = editTextEmail.getText().toString();
        editor.putString("EMAIL", email);

        editor.apply();
    }
}