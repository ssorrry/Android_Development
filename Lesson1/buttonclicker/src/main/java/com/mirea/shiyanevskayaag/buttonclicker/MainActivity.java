package com.mirea.shiyanevskayaag.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textViewStudent;
    private Button btnWhoAmI;
    private Button btnItIsNotMe ;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStudent = (TextView) findViewById(R.id.tvOut3);
        btnWhoAmI = (Button) findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = (Button) findViewById(R.id.btnItIsNotMe);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку №27 (вроде бы)");
                checkBox.setChecked(true);
            }
        };

        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }
    public void onMyButtonClick(View view)
    {
        Toast.makeText(this,"Это не я сделал!", Toast.LENGTH_SHORT).show();
        textViewStudent.setText("Это не я сделал!");
        checkBox.setChecked(false);
    }
}