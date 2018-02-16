package com.thonysupersonic.redditclient.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.model.BeRedditObject;
import com.thonysupersonic.redditclient.view.fragments.RedditDetailsFragment;

public class Details extends AppCompatActivity {


    String title = "DETAILS";
    Toolbar toolbar;

    BeRedditObject object;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //setting my toolbar as an actionbar

        setTitle(title); //setting the title

        object = (BeRedditObject) getIntent().getSerializableExtra("redditObject");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        loadFragment();
    }

    private void loadFragment(){
        Fragment fragment = RedditDetailsFragment.createNewInstance(object);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transac =  manager.beginTransaction();
        transac.replace(R.id.fragmentDetails, fragment );
        transac.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu
        );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.actionShare:
                share();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void share(){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, "http://www.reddit.com" + object.permalink);

        if(i.resolveActivity(getPackageManager()) != null){
            startActivity(Intent.createChooser(i, "Share URL"));
        }
    }
}
