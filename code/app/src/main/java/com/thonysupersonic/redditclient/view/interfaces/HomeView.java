package com.thonysupersonic.redditclient.view.interfaces;

import com.thonysupersonic.redditclient.model.BeReddit;

import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public interface HomeView {
    void onRedditListReady(List<BeReddit> redditList);
    void onRedditListFail(String message);

}
