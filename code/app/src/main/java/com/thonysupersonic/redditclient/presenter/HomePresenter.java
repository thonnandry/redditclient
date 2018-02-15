package com.thonysupersonic.redditclient.presenter;

/**
 * Created by anthony on 2/15/18.
 */

public interface HomePresenter {

    void getPaginnatedRedditList(int after, int limit);
}
