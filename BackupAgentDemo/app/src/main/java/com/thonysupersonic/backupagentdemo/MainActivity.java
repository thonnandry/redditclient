package com.thonysupersonic.backupagentdemo;

import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BackupManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }


    public void save(View v){
        manager  = new BackupManager(this);
        SharedPreferences pref = getSharedPreferences(BackupClass.PRES_TEST, Context.MODE_PRIVATE);
        pref.edit().putString("cloud", "Anthonyyy").commit();

        manager.dataChanged();
    }

    public void read(View v){
        manager  = new BackupManager(this);
        SharedPreferences pref = getSharedPreferences(BackupClass.PRES_TEST, Context.MODE_PRIVATE);

        Toast.makeText(this, pref.getString("cloud", ""), Toast.LENGTH_SHORT).show();
    }
}
