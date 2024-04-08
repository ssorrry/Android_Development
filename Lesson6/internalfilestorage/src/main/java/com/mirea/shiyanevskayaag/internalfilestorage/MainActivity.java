package com.mirea.shiyanevskayaag.internalfilestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String fileName = "mirea.txt";

    EditText editTextYear, editTextDescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextYear = (EditText) findViewById(R.id.year);
        editTextDescr = (EditText) findViewById(R.id.descr);
    }

    public void OnButtonClick(View view)
    {
        String year = editTextYear.getText().toString();
        String descr = editTextDescr.getText().toString();

        String text = year + " - " + descr;

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}