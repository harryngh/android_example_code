package com.highstarapp.musicplayerservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button playButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton=findViewById(R.id.playBtn);
        stopButton=findViewById(R.id.stopBtn);
        playButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        if(v==playButton){
            startService(new Intent(this,MusicService.class));
        }
        else if (v==stopButton){
            stopService(new Intent(this,MusicService.class));
        }
    }
}
