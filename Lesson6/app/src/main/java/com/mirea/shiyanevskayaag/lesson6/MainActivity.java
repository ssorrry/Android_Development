package com.mirea.shiyanevskayaag.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextGroup, editTextNumber, editTextMovie;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGroup = findViewById(R.id.group);
        editTextNumber = findViewById(R.id.number);
        editTextMovie = findViewById(R.id.movie);

        sharedPref = getSharedPreferences("mirea_settings",	Context.MODE_PRIVATE);

        String groupSave = sharedPref.getString("GROUP ", "unknown");
        int numberSave = sharedPref.getInt("NUMBER ", 0);
        String movieSave = sharedPref.getString("MOVIE ", "unknown");

        if(!groupSave.equals("unknown"))
        {
            editTextGroup.setText(groupSave);
        }

        if(numberSave != 0)
        {
            editTextNumber.setText(String.valueOf(numberSave));
        }

        if(!movieSave.equals("unknown"))
        {
            editTextMovie.setText(movieSave);
        }
    }

    public void OnButtonClick(View view)
    {
        SharedPreferences.Editor editor	= sharedPref.edit();

        String group = editTextGroup.getText().toString();
        editor.putString("GROUP", group);

        String numberString = editTextNumber.getText().toString();
        try {
            int number = Integer.parseInt(numberString);
            editor.putInt("NUMBER", number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String movie = editTextMovie.getText().toString();
        editor.putString("MOVIE", movie);

        editor.apply();
    }
}