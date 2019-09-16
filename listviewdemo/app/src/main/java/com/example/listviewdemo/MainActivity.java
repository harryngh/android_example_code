package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> courses;
    ArrayAdapter theAdapter;
    int selectedItemIndex=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        courses= new ArrayList<>();
        courses.add("Operating Systems");
        courses.add("Discrete Mathematics");
        courses.add("Database");
        courses.add("Web Development");
        courses.add("Mobile App Development");

//        courses.add("Operating Systems 2");
//        courses.add("Discrete Mathematics 2");
//        courses.add("Database 2");
//        courses.add("Web Development 2");
//        courses.add("Mobile App Development 2");

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.inputText);

                String theText= editText.getText().toString();
                courses.add(theText);
                theAdapter.notifyDataSetChanged();

            }
        });

        Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.inputText);
                String theText= editText.getText().toString();
                courses.set(selectedItemIndex,theText);
                theAdapter.notifyDataSetChanged();
            }
        });


        theAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,courses);
        final ListView courseLV = findViewById(R.id.courseLV);
        courseLV.setAdapter(theAdapter);

        courseLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String theItem = "Click: " + courses.get(i);
                Toast.makeText(MainActivity.this,theItem,Toast.LENGTH_SHORT).show();
                EditText editText = findViewById(R.id.inputText);
                editText.setText(courses.get(i));
                selectedItemIndex=i;
            }
        });

        courseLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                courses.remove(i);
                theAdapter.notifyDataSetChanged();
                return false;
            }
        });

        final String [] countries = {"UAE","Viet Nam","Thailand","Malaysia","Indonesia"};
        Spinner groupGSpinner = findViewById(R.id.groupGSpinner);
        ArrayAdapter spinAdapter =
                new ArrayAdapter(this,android.R.layout.simple_spinner_item,countries);
        groupGSpinner.setAdapter(spinAdapter);
        groupGSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country = countries[i];
                courses.add(country);
                theAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
