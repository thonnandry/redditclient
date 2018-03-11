package com.thonysupersonic.redditclient.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.io.Serializable;

/**
 * Created by anthony on 2/15/18.
 */

@JsonObject
public class BeRedditObject implements Serializable {

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
    @JsonField
    public int created;
    @JsonField
    public String permalink;
	
	//comentario de Oscar


}
