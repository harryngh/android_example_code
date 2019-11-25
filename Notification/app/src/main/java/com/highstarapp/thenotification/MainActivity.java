package com.highstarapp.thenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final int NOTIFICATION_ID = 001;
    private final String CHANNEL_ID = "TheNotification";

    private final String CHANNEL_NAME = "The Notification";
    private final String CHANNEL_DESC = "The Notification System for The App";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public void displayNotificationMsg(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Display Notification");
        builder.setMessage("Do you really want to display notification?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        displayNotification();
                    }
                });

        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void displayNotification(){
        Intent landingIntent= new Intent(this,LandingActivity.class);
//        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,
                0,landingIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("The Notification");
        builder.setContentText("This is the content of the notification");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        builder.setAutoCancel(true);
        builder.setContentIntent(landingPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat
                .from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }
}
