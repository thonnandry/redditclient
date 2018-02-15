package com.thonysupersonic.redditclient.presenter;

/**
 * Created by anthony on 2/15/18.
 */

public interface RedditListPresenter {

    void getPaginnatedRedditList(String after, int limit);
}
