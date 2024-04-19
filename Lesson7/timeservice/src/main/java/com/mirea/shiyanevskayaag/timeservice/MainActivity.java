package com.mirea.shiyanevskayaag.timeservice;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mirea.shiyanevskayaag.timeservice.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final String host = "time.nist.gov"; // или time-a.nist.gov
    private final int port = 13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTimeTask timeTask = new GetTimeTask();
                timeTask.execute();
            }
        });
    }
    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String timeResult = "";
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine(); // игнорируем первую строку
                timeResult = reader.readLine(); // считываем вторую строку
                Log.d(TAG,timeResult);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return timeResult;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String time = parseTime(result);
            String date = parseDate(result);
            binding.date.setText(date);
            binding.time.setText(time);
        }
        public String parseTime(String timeString) {
            // Разбиваем строку по пробелам
            String[] parts = timeString.split("\\s+");
            if (parts.length >= 3) {
                // Время находится в третьей части
                return parts[2];
            } else {
                return "Invalid time format";
            }
        }

        public String parseDate(String timeString) {
            // Разбиваем строку по пробелам
            String[] parts = timeString.split("\\s+");
            if (parts.length >= 2) {
                // Дата находится во второй части
                return parts[1];
            } else {
                return "Invalid date format";
            }
        }
    }
}
