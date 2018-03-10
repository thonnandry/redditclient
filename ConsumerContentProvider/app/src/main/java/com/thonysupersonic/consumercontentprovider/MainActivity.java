package com.thonysupersonic.consumercontentprovider;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView result_app1 = findViewById(R.id.app1_result);
//        TextView result_app2 = findViewById(R.id.app2_result);

        final String app1UriString = "content://com.androidatc.exposedcontent.provider/t1";
        final String app2UriString = "content://com.androidatc.exposedcontent.provider/t1";

        String cols[] = new String[]{"_id", "MESSAGE"};

        Uri u = Uri.parse(app1UriString);

        Cursor c = getContentResolver().query(u, cols, null, null, null);

        if(c.moveToFirst()){
            result_app1.setText("Content accessed: " + c.getString(c.getColumnIndex("MESSAGE")));

        }else{
            result_app1.setText("Access denied : Secuirity Exception");
        }

//        u = Uri.parse(app2UriString);
//        try{
//            c = getContentResolver().query(u, cols, null, null, null);
//
//            if(c.moveToFirst()){
//                result_app2.setText("Content accessed : " + c.getString(c.getColumnIndex("MESSAGE")));
//            }else{
//                result_app2.setText("Cannot access content");
//            }
//        }catch (SecurityException e){
//            result_app2.setText("Access denied : Secuirity Exception");
//        }
    }
}
