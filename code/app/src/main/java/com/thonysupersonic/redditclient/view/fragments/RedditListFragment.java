package com.thonysupersonic.redditclient.view.fragments;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.model.BeRedditRoot;
import com.thonysupersonic.redditclient.presenter.RedditListPresenterImpl;
import com.thonysupersonic.redditclient.view.activities.Details;
import com.thonysupersonic.redditclient.view.adapters.RedditAdapter;
import com.thonysupersonic.redditclient.view.interfaces.RedditListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public class RedditListFragment extends Fragment implements RedditListView, AdapterView.OnItemClickListener {

    ListView lstTopReddit;
    RedditAdapter adapter;
    ArrayList<BeRedditRoot> redditList;
    RedditListPresenterImpl presenter;
    String after  = "";
    int limit = 25;


    SwipeRefreshLayout swipeRefreshLayout;
    boolean isSplit = false;
    ArrayList<String> viewed;
    ArrayList<String> dismissed;
    ArrayList<BeRedditRoot> favoriteList;


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

        checkSplitMode();

        lstTopReddit = view.findViewById(R.id.lstTopReddit);

        initListView(savedInstanceState);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        initSwipe();

        presenter = new RedditListPresenterImpl(this); //create a presenter instance
        presenter.getPaginnatedRedditList(after, limit);

        swipeRefreshLayout.setRefreshing(true);

        setHasOptionsMenu(true);
    }


    public void checkSplitMode(){
        View v = getView().findViewById(R.id.containerDetails);
        isSplit =  v != null && v.getVisibility() == View.VISIBLE;
    }

    public void showDetails(int pos){
        if(isSplit){
            Fragment fragment = RedditDetailsFragment.createNewInstance(redditList.get(pos).data);
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transac =  manager.beginTransaction();
            transac.replace(R.id.containerDetails, fragment );
            transac.commit();
        }else{
            Intent i = new Intent(getContext(), Details.class);
            i.putExtra("redditObject", redditList.get(pos).data);
            startActivity(i);

        }

        viewed.add(redditList.get(pos).data.name);
        adapter.notifyDataSetChanged();
    }


    private void initListView(Bundle savedInstanceState){


        if(savedInstanceState != null) {
            redditList = (ArrayList<BeRedditRoot>) savedInstanceState.getSerializable("redditList");
            viewed = (ArrayList<String>) savedInstanceState.getSerializable("viewed");
            after =  savedInstanceState.getString("after");
            favoriteList = (ArrayList<BeRedditRoot>) savedInstanceState.getSerializable("favoriteList");
            dismissed = (ArrayList<String>) savedInstanceState.getSerializable("dismissed");
        }else{
            redditList = new ArrayList<>();
            viewed = new ArrayList<>();
            favoriteList = new ArrayList<>();
            dismissed = new ArrayList<>();
        }


        adapter = new RedditAdapter(getActivity(), redditList, viewed);
        lstTopReddit.setAdapter(adapter);
        lstTopReddit.setOnItemClickListener(this);

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


        registerForContextMenu(lstTopReddit);

        
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
            for(BeRedditRoot rootObJECT : list) {
                if(!dismissed.contains(rootObJECT.data.name)) // in order no ignored previously dismissed items
                    redditList.add(rootObJECT);
            }


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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        showDetails(i);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("viewed", viewed);
        outState.putSerializable("redditList", redditList);
        outState.putSerializable("after", after);
        outState.putSerializable("dismissed", dismissed);
        outState.putSerializable("favoriteList", favoriteList);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.main_context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //get the position
        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(item.getItemId() == R.id.actionDismiss){
            dismissed.add(redditList.get(contextMenuInfo.position).data.name); //add the name of the dismissed item
            redditList.remove(contextMenuInfo.position);
            adapter.notifyDataSetChanged();
        }else if (item.getItemId() == R.id.actionFavorite){
            favoriteList.add(redditList.get(contextMenuInfo.position));
            Toast.makeText(getContext(), "Item added to your favorites.", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.actionFavorite){
            showFavorites();
        }

        return super.onOptionsItemSelected(item);
    }


    public void showFavorites(){

        //for the sake of simplicity i create everything dinamically

        View v = getLayoutInflater().from(getContext()).inflate(R.layout.dialog_favorites, null);

        ListView lstFavorites =  v.findViewById(R.id.lstFavorites);
        RedditAdapter adapterFavorites = new RedditAdapter(getContext(), favoriteList, new ArrayList<String>());
        lstFavorites.setAdapter(adapterFavorites);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Favorites");
        builder.setView(v);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }
}
