package com.mirea.shiyanevskayaag.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editTextFilename, editTextQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextFilename = (EditText) findViewById(R.id.filename);
        editTextQuote = (EditText) findViewById(R.id.quote);

    }

    public void OnButtonSaveClick(View view)
    {
        String filename = editTextFilename.getText().toString();
        String quote = editTextQuote.getText().toString();

        if(isExternalStorageWritable())
        {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, filename + ".txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
                OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
                output.write(quote);
                output.close();
            } catch (IOException e) {
                Log.w("ExternalStorage", "Error writing " + file, e);
            }
        }
    }

    public void OnButtonLoadClick(View view)
    {
        String filename = editTextFilename.getText().toString();

        if(isExternalStorageReadable())
        {
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(path, filename + ".txt");
            try {
                FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                List<String> lines = new ArrayList<String>();
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                StringBuilder stringBuilder = new StringBuilder();
                for (String str : lines) {
                    stringBuilder.append(str).append("\n");
                }
                editTextQuote.setText(stringBuilder.toString());

                Log.w("ExternalStorage", String.format("Read from file %s successful", lines.toString()));
            } catch (Exception e) {
                Log.w("ExternalStorage", String.format("Read from file %s failed", e.getMessage()));
            }

        }
    }


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

}