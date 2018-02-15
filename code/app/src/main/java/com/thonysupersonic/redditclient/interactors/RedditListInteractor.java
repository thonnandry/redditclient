package com.thonysupersonic.redditclient.interactors;

import com.thonysupersonic.redditclient.model.BeRedditRoot;

import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public interface RedditListInteractor {

    void getPagginatedRedditList(String after, int limit, OnRedditListFinishedListener callback);

     interface  OnRedditListFinishedListener{
        void onPagginatedRedditListReady(List<BeRedditRoot> redditList);
        void onPagginatedRedditListFail(String message);
    }

}
