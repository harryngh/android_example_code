package com.highstarapp.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SecondFragment.MessageSendListener {

    public static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null) {
                return;
            }
            FirstFragment firstFragment = new FirstFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,firstFragment,null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onMessage(String message) {
        TextView displayMsgTV = findViewById(R.id.displaying_message_TV);
        displayMsgTV.setText(message);
    }
}
