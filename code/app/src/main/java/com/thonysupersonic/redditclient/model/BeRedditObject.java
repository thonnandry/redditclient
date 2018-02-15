package com.thonysupersonic.redditclient.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by anthony on 2/15/18.
 */

@JsonObject
public class BeRedditObject {

    @JsonField
    public String author;
    @JsonField
    public String id;
    @JsonField
    public int score;
    @JsonField
    public String thumbnail;
    @JsonField
    public int downs;
    @JsonField
    public int ups;
    @JsonField
    public int num_comments;
    @JsonField
    public boolean isVideo;
    @JsonField
    public String title;
    @JsonField
    public String url;
    @JsonField
    public String name;
    @JsonField
    public int created_utc;


}
