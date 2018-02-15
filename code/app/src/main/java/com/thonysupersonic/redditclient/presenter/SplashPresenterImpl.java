package com.thonysupersonic.redditclient.presenter;

import android.content.Context;
import android.view.View;

import com.thonysupersonic.redditclient.view.interfaces.SplashView;

/**
 * Created by anthony on 2/15/18.
 */

public class SplashPresenterImpl implements SplashPresenter {

    SplashView v;

    public SplashPresenterImpl( SplashView view){
        this.v = view; //i receive a SplashView instance and save it
    }


    @Override
    public void waitSeconds() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    //after waiting i communicate my view that the job is done
                    v.onLoadFinished();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start(); //start a thread with a 3 seconds duration
    }
}
