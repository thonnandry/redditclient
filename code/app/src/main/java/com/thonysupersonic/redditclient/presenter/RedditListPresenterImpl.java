package com.thonysupersonic.redditclient.presenter;

import com.thonysupersonic.redditclient.interactors.RedditListInteractorImpl;
import com.thonysupersonic.redditclient.model.BeRedditRoot;
import com.thonysupersonic.redditclient.interactors.RedditListInteractor;
import com.thonysupersonic.redditclient.view.interfaces.RedditListView;

import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public class RedditListPresenterImpl implements RedditListPresenter, RedditListInteractor.OnRedditListFinishedListener {

    RedditListView v;
    RedditListInteractor interactor;

    public RedditListPresenterImpl(RedditListView view){
        this.v = view;
        interactor = new RedditListInteractorImpl(); //instantiate my model
    }

    @Override
    public void getPaginnatedRedditList(String after, int limit) {
        interactor.getPagginatedRedditList(after, limit, this); //call my method on the model to get reddit entries
    }

    @Override
    public void onPagginatedRedditListReady(List<BeRedditRoot> redditList) {
        //finish getting information
        v.onRedditListReady(redditList); //pass the list of objects to the view
    }

    @Override
    public void onPagginatedRedditListFail(String message) {

    }
}
