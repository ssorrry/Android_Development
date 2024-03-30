package com.mirea.shiyanevskayaag.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mirea.shiyanevskayaag.thread.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private	ActivityMainBinding	binding;
    private	int	counter	=	0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding	=	ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.textView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-09-21, НОМЕР ПО СПИСКУ: 27, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Гордость и предубеждение и зомби");
        binding.textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(),	"Stack:	"	+	Arrays.toString(mainThread.getStackTrace()));

        binding.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int	numberThread = counter++;
                        Log.d("ThreadProject",	String.format("Запущен поток № %d студентом группы № %s номер по списку № %d", numberThread, "БСБО-09-21", 27));
                        long endTime = System.currentTimeMillis() +	20 * 1000;
                        while	(System.currentTimeMillis()	< endTime){
                            synchronized(this){
                                try	{
                                    wait(endTime	- System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(),"Endtime:	" + endTime);
                                }	catch(Exception	e){
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject",	"Выполнен поток № " + numberThread);
                        }
                    }
                }).start();
            }
        });

    }
}