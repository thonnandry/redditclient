package com.thonysupersonic.redditclient.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.thonysupersonic.redditclient.R;

public class Home extends AppCompatActivity {

    String title = "TOP";
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //setting my toolbar as an actionbar

        setTitle(title); //setting the title
        AppCenter.start(getApplication(), "16e2dbb1-ae79-45e0-a628-e0638f10602e",
                Analytics.class, Crashes.class);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //setting the main menu
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu); //in order to allow fragments set their own menus]
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
