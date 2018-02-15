package com.thonysupersonic.redditclient.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.model.BeReddit;
import com.thonysupersonic.redditclient.presenter.HomePresenter;
import com.thonysupersonic.redditclient.presenter.HomePresenterImpl;
import com.thonysupersonic.redditclient.view.interfaces.HomeView;

import java.util.List;

public class Home extends AppCompatActivity  implements HomeView{

    String title;
    Toolbar toolbar;
    HomePresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //setting my toolbar as an actionbar

        setTitle(title); //setting the title

        presenter = new HomePresenterImpl(this); //instantiate my presenter object

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //setting the main menu
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu); //in order to allow fragments set their own menus]
    }

    @Override
    public void onRedditListReady(List<BeReddit> redditList) {

    }

    @Override
    public void onRedditListFail(String message) {

    }
}
