package com.mirea.shiyanevskayaag.lesson3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String string = intent.getStringExtra("message");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Квадрат значения моего номера по списку в группе составляет 729, а текущее время " + string);
    }
}