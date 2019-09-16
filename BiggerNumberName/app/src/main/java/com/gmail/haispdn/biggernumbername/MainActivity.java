package com.gmail.haispdn.biggernumbername;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int points = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton1Click(View view){
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        int num1 = Integer.parseInt(btn1.getText().toString());
        int num2 = Integer.parseInt(btn2.getText().toString());
        if (num1 >= num2) {
            points++;
            Toast.makeText(this, "Right answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
            points--;
        }

        TextView pointsTV = findViewById(R.id.pointsTV);
        pointsTV.setText("Points: "+points);
        generateRandomNums();
    }


    private void generateRandomNums(){
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        int num1 = new Random().nextInt(100);
        int num2 = new Random().nextInt(100);
        btn1.setText(String.valueOf(num1));
        btn2.setText(String.valueOf(num2));
    }
    public void onButton2Click(View view){
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        int num1 = Integer.parseInt(btn1.getText().toString());
        int num2 = Integer.parseInt(btn2.getText().toString());
        if (num1 <= num2) {
            points++;
            Toast.makeText(this, "Right answer", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
            points--;
        }
        TextView pointsTV = findViewById(R.id.pointsTV);
        pointsTV.setText("Points: "+points);
        generateRandomNums();
    }
}
