package com.thonysupersonic.redditclient.view.interfaces;

import com.thonysupersonic.redditclient.model.BeRedditRoot;

import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public interface RedditListView {
    void onRedditListReady(List<BeRedditRoot> redditList);
    void onRedditListFail(String message);

}
