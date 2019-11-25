package com.highstarapp.runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA = 125;
    private static final int REQUEST_STORAGE = 225;
    private static final int REQUEST_CONTACTS = 325;
    private static final int REQUEST_GROUP_PERMISSIONS = 425;

    private static final int TXT_CAMERA = 1;
    private static final int TXT_STORAGE = 2;
    private static final int TXT_CONTACTS = 3;

    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionUtil=new PermissionUtil(this);
    }

    private int checkPermission(int permission){
        int status = PackageManager.PERMISSION_DENIED;
        switch (permission){
            case TXT_CAMERA:
                status= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                break;
            case TXT_STORAGE:
                status= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case TXT_CONTACTS:
                status= ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
                break;
        }
        return status;
    }

    private void requestPermission(int permission){
        switch (permission){
            case TXT_CAMERA:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                break;
            case TXT_STORAGE:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
                break;
            case TXT_CONTACTS:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CONTACTS);
                break;
        }
    }

    private void showPermissionExplanation(final int permission){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        if(permission==TXT_CAMERA){
            builder.setMessage("This app need to access your device camera.. Please allow");
            builder.setTitle("Camera Permission Needed...");
        }
        if(permission==TXT_STORAGE){
            builder.setMessage("This app need to access your device storage.. Please allow");
            builder.setTitle("STORAGE Permission Needed...");
        }
        if(permission==TXT_CONTACTS){
            builder.setMessage("This app need to access your device contacts.. Please allow");
            builder.setTitle("Contacts Permission Needed...");
        }

        builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(permission==TXT_CAMERA)
                    requestPermission(TXT_CAMERA);
                else if(permission==TXT_STORAGE)
                    requestPermission(TXT_STORAGE);
                else if(permission==TXT_CONTACTS)
                    requestPermission(TXT_CONTACTS);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void openCamera(View view) {
        if(checkPermission(TXT_CAMERA)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CAMERA))
                showPermissionExplanation(TXT_CAMERA);
            else if(!permissionUtil.checkPermissionPreference("camera")) {
                requestPermission(TXT_CAMERA);
                permissionUtil.updatePermissionPreference("camera");
            }
            else{
                Toast.makeText(this,"Please allow camera permission if your app settings",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }
        else{
            Toast.makeText(this,"You have camera permission",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("message","You can now take photos and record videos...");
            this.startActivity(intent);
        }
    }

    public void rightToStorage(View view) {
        if(checkPermission(TXT_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE))
                showPermissionExplanation(TXT_STORAGE);
            else if(!permissionUtil.checkPermissionPreference("storage")) {
                requestPermission(TXT_STORAGE);
                permissionUtil.updatePermissionPreference("storage");
            }
            else{
                Toast.makeText(this,"Please allow storage permission if your app settings",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }
        else{
            Toast.makeText(this,"You have storage permission",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("message","You can now write to external devices on the phone...");
            this.startActivity(intent);
        }
    }

    public void readContacts(View view) {
        if(checkPermission(TXT_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CONTACTS))
                showPermissionExplanation(TXT_CONTACTS);
            else if(!permissionUtil.checkPermissionPreference("contacts")) {
                requestPermission(TXT_CONTACTS);
                permissionUtil.updatePermissionPreference("contacts");
            }
            else{
                Toast.makeText(this,"Please allow contacts permission if your app settings",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package",this.getPackageName(),null);
                intent.setData(uri);
                this.startActivity(intent);
            }
        }
        else{
            Toast.makeText(this,"You have contacts permission",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("message","You can read contacts on the phone...");
            this.startActivity(intent);
        }
    }

    private void requestGroupPermissions(ArrayList<String> permissions){
        String [] permissionList = new String[permissions.size()];
        permissions.toArray(permissionList);
        if(permissionList.length>0)
            ActivityCompat.requestPermissions(MainActivity.this,permissionList,REQUEST_GROUP_PERMISSIONS);
    }

    public void requestAllPermissions(View view) {
        ArrayList<String> permissionsNeeded= new ArrayList<>();
        ArrayList<String> permissionAvailable = new ArrayList<>();
        permissionAvailable.add(Manifest.permission.READ_CONTACTS);
        permissionAvailable.add(Manifest.permission.CAMERA);
        permissionAvailable.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        for(String permission:permissionAvailable){
            if(ContextCompat.checkSelfPermission(this,permission)!=
            PackageManager.PERMISSION_GRANTED)
                permissionsNeeded.add(permission);
        }
        requestGroupPermissions(permissionsNeeded);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CAMERA:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You have camera permission",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("message", "You can now take photos and record videos...");
                    this.startActivity(intent);
                }
                else{
                    Toast.makeText(this,"Camera permission is denied. Turn off the camera features of the app",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_STORAGE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You have storage permission",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("message", "You can now write to external devices on the phone...");
                    this.startActivity(intent);
                }
                else{
                    Toast.makeText(this,"Storage permission is denied. Turn off the storage features of the app",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_CONTACTS:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "You have contacts permission",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("message", "You can read contacts on the phone...");
                    this.startActivity(intent);
                }
                else{
                    Toast.makeText(this,"Contacts permission is denied. Turn off the contacts features of the app",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case REQUEST_GROUP_PERMISSIONS:
                String result="";
                int i=0;
                for(String perm:permissions){
                    String status = "";
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED)
                        status="Granted";
                    else
                        status="Denied";
                    result = result+"\n"+perm+": "+status;
                    i++;
                }
                AlertDialog.Builder builder= new AlertDialog.Builder(this);

                builder.setTitle("Group permission Details: ");
                builder.setMessage(result);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
