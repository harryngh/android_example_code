package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Brand> brands;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView socialBrandsLV=findViewById(R.id.brandLV);
        //Sources
        brands= generateDataSource();
        //Adapter
        BrandAdapter theAdapter = new BrandAdapter(brands,this);
        socialBrandsLV.setAdapter(theAdapter);

    }

    private ArrayList<Brand> generateDataSource() {
        ArrayList<Brand> thebrands = new ArrayList<>();
        thebrands.add(
                new Brand("Facebook","No 1 social site",
                        R.drawable.facebook));
        thebrands.add(
                new Brand("Instagram","No 1 image site",
                        R.drawable.instagram));
        thebrands.add(
                new Brand("Twitter","No 1 news site",
                        R.drawable.twitter));
        thebrands.add(
                new Brand("Whatsapp","No 1 chat app",
                        R.drawable.whatsapp));
        thebrands.add(
                new Brand("Youtube","No 1 video site",
                        R.drawable.youtube));
        return thebrands;
    }
}
