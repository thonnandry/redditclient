package com.thonysupersonic.redditclient.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.presenter.SplashPresenterImpl;
import com.thonysupersonic.redditclient.view.interfaces.SplashView;

public class SplashScreen extends AppCompatActivity implements SplashView {


    //I declare the presenter implementation variable
    SplashPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        presenter = new SplashPresenterImpl(this); //instantiate my presenter object
        presenter.waitSeconds(); //i call de waitseconds method to wait 3 seconds
    }

    @Override
    public void onLoadFinished() {
        //I redirect to the Home activity when the thread is finished
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }
}
