package com.thonysupersonic.applicationpermissionconsumer;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            Context otherContext = createPackageContext("com.thonysupersonic.applicationwithpermission", CONTEXT_IGNORE_SECURITY);

            InputStream stream = otherContext.openFileInput("archivo.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String linea = "";
            StringBuilder builder = new StringBuilder();
            while ((linea = reader.readLine()) != null){
                    builder.append(linea);
            }
            Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void abrir(View v){

        if(ContextCompat.checkSelfPermission(this, "com.thonysupersonic.applicationwithpermission.START_APP") != PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, "com.thonysupersonic.applicationwithpermission.START_APP")){
                ActivityCompat.requestPermissions(this, new String[]{"com.thonysupersonic.applicationwithpermission.START_APP"}, 1);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{"com.thonysupersonic.applicationwithpermission.START_APP"}, 1);
            }

        }else{
            Intent i = new Intent( "com.thony.actividadprotegida");
            startActivity(i);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){
                Intent i = new Intent( "com.thony.actividadprotegida");
                startActivity(i);
            }
        }
    }
}
