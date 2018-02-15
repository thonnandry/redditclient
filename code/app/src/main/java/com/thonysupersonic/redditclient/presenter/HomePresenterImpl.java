package com.thonysupersonic.redditclient.presenter;

import com.thonysupersonic.redditclient.view.interfaces.HomeView;

/**
 * Created by anthony on 2/15/18.
 */

public class HomePresenterImpl implements HomePresenter {

    HomeView v;

    public HomePresenterImpl(HomeView view){
        this.v = view;
    }

    @Override
    public void getPaginnatedRedditList(int after, int limit) {
        
    }
}
