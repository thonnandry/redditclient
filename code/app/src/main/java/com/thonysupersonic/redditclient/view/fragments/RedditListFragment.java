package com.thonysupersonic.redditclient.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;


import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.model.BeRedditRoot;
import com.thonysupersonic.redditclient.presenter.RedditListPresenterImpl;
import com.thonysupersonic.redditclient.view.adapters.RedditAdapter;
import com.thonysupersonic.redditclient.view.interfaces.RedditListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public class RedditListFragment extends Fragment implements RedditListView {

    ListView lstTopReddit;
    RedditAdapter adapter;
    List<BeRedditRoot> redditList;
    RedditListPresenterImpl presenter;
    String after  = "";
    int limit = 25;


    SwipeRefreshLayout swipeRefreshLayout;


    public static RedditListFragment createNewInstance(){
        return new RedditListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.reddit_list_fragment, null);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        lstTopReddit = view.findViewById(R.id.lstTopReddit);
        initListView();

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        initSwipe();

        presenter = new RedditListPresenterImpl(this); //create a presenter instance
        presenter.getPaginnatedRedditList(after, limit);

        swipeRefreshLayout.setRefreshing(true);
    }


    private void initListView(){
        redditList = new ArrayList<>();
        adapter = new RedditAdapter(getActivity(), redditList);
        lstTopReddit.setAdapter(adapter);
        lstTopReddit.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (lstTopReddit.getLastVisiblePosition() - lstTopReddit.getHeaderViewsCount() -
                        lstTopReddit.getFooterViewsCount()) >= (adapter.getCount() - 1)) {

                        swipeRefreshLayout.setRefreshing(true);
                        presenter.getPaginnatedRedditList(after, limit);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });



        
    }

    private void initSwipe(){
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //on refresh i load items from stractch
                        after = "";
                        redditList.clear();
                        adapter.notifyDataSetChanged();
                        presenter.getPaginnatedRedditList(after, limit);
                    }
                }
        );

    }

    @Override
    public void onRedditListReady(List<BeRedditRoot> list) {
        swipeRefreshLayout.setRefreshing(false);
        if(list.size() > 0){
            //if we have results

            //update the list
            redditList.addAll(list);
            adapter.notifyDataSetChanged();

            //get the last items name in order to paginate from its position the next time
            after = list.get(list.size() - 1).data.name;
        }else{
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onRedditListFail(String message) {

    }
}
