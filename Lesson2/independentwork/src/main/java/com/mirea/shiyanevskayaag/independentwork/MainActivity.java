package com.mirea.shiyanevskayaag.independentwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTimePickerDialog(View view)
    {
        MyTimeDialogFragment timeDialogFragment = new MyTimeDialogFragment();
        TextView textView = findViewById(R.id.textView);
        timeDialogFragment.setTextView(textView);
        timeDialogFragment.show(getSupportFragmentManager(), "time_dialog");
    }

    public void onClickDatePickerDialog(View view)
    {
        MyDateDialogFragment dateDialogFragment = new MyDateDialogFragment();
        TextView textView = findViewById(R.id.textView2);
        dateDialogFragment.setTextView(textView);
        dateDialogFragment.show(getSupportFragmentManager(), "time_dialog");
    }

    public void onClickShowProgressDialog(View view) {
        MyProgressDialogFragment progressDialogFragment = new MyProgressDialogFragment();
        progressDialogFragment.show(getSupportFragmentManager(), "progress_dialog");
    }
}