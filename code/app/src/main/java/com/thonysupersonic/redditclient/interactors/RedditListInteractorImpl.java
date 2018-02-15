package com.thonysupersonic.redditclient.interactors;

import com.bluelinelabs.logansquare.LoganSquare;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.thonysupersonic.redditclient.model.BeRedditRoot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by anthony on 2/15/18.
 */

public class RedditListInteractorImpl implements RedditListInteractor {


    @Override
    public void getPagginatedRedditList(String after, int limit, final RedditListInteractor.OnRedditListFinishedListener callback) {

        RequestParams params = new RequestParams();
        params.put("after", after);
        params.put("limit", limit);

        RESTHelper.get("top/.json", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray


                try {
                    if(response.has("data")) {
                        JSONObject data = response.getJSONObject("data");
                        if(data.has("children")){
                            List<BeRedditRoot> list = LoganSquare.parseList(data.getJSONArray("children").toString(), BeRedditRoot.class);
                            callback.onPagginatedRedditListReady(list);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {



            }
        });
    }
}
