package com.thonysupersonic.readsmsapp;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;



public class MainActivity extends AppCompatActivity {


    ListView lstSMS;
    public static Uri URI_SMS = Uri.parse("content://sms");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstSMS = findViewById(R.id.lstSMS);

        pedirPermiso();
    }

    private void pedirPermiso(){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
            }

        }else {
            cargarCursor();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                cargarCursor();
            }
        }
    }

    private void cargarCursor(){
        Cursor ccursor = getContentResolver().query(URI_SMS, new String[]{
                SmsColumns.ID, SmsColumns.ADDRESS, SmsColumns.DATE, SmsColumns.BODY
        }, null, null, SmsColumns.DATE + " DESC");

        SmsCursorAdapter adapter = new SmsCursorAdapter(this, ccursor, true);
        lstSMS.setAdapter(adapter);
    }


    public class SmsColumns{
        public static final String ID = "_id";
        public static final String ADDRESS = "address";
        public static final String DATE = "date";
        public static final String BODY = "body";
    }
}
