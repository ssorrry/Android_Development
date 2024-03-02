package com.mirea.shiyanevskayaag.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.textViewStudent);

        myTextView.setText("New text in MIREA");*/

        setContentView(R.layout.activity_second);

        EditText editText = (EditText) findViewById(R.id.editTextText2);
        editText.setText("New edit life for mirea activity!");

        Button button = (Button) findViewById(R.id.button7);
        button.setText("MireaButton");

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox1);
        checkBox.setChecked(true);

    }
}