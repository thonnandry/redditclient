package com.thonysupersonic.redditclient.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by anthony on 2/15/18.
 */

@JsonObject
public class BeRedditRoot implements Serializable{
    @JsonField
    public String kind;
    @JsonField
    public BeRedditObject data;

}
