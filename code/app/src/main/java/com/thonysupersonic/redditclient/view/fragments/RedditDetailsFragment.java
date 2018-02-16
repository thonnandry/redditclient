package com.thonysupersonic.redditclient.view.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.model.BeRedditObject;
import com.thonysupersonic.redditclient.view.activities.ImageFullscreen;
import com.thonysupersonic.redditclient.view.utilities.Functions;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by anthony on 2/15/18.
 */

public class RedditDetailsFragment extends Fragment {

    BeRedditObject object;
    TextView lblTitulo;
    GifImageView imgThumbnail;
    ProgressBar loading;
    TextView lblCreatedAt;
    TextView lblDownVotes;
    TextView lblUpVotes;
    TextView lblComments;

    public static RedditDetailsFragment createNewInstance(BeRedditObject object){

        Bundle bundle = new Bundle();
        bundle.putSerializable("redditObject", object);

        RedditDetailsFragment fragment = new RedditDetailsFragment();
        fragment.setArguments(bundle);
        return  fragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.details_fragment, null);
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        object =  (BeRedditObject) getArguments().getSerializable("redditObject");


        lblTitulo = view.findViewById(R.id.lblTitle);
        lblComments = view.findViewById(R.id.lblComments);
        lblDownVotes = view.findViewById(R.id.lblDownvote);
        lblUpVotes = view.findViewById(R.id.lblUpvote);
        lblCreatedAt = view.findViewById(R.id.lblCreatedBy);

        imgThumbnail = view.findViewById(R.id.imgThumbnail);


        initData(view);

    }


    private void initData(View v){
        lblTitulo.setText(object.title);
        lblComments.setText(String.valueOf(object.num_comments));
        lblDownVotes.setText(String.valueOf(object.downs));
        lblUpVotes.setText(String.valueOf(object.ups));



        lblCreatedAt.setText("By " + object.author + " - " + Functions.convertUTCTime(object.created));


        loading = v.findViewById(R.id.loading);
        imgThumbnail = v.findViewById(R.id.imgFullscreen);


        imgThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iFullscreen = new Intent(getContext(), ImageFullscreen.class);
                iFullscreen.putExtra("urlImagen", object.url);
                getContext().startActivity(iFullscreen);
            }
        });


        if(object.url.contains("http") && !object.url.contains(".html")) {

            if (object.url.contains(".gifv")) {
                object.url = object.url.replace(".gifv", ".gif");
            }

            Glide.with(this).load(object.url).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    loading.setVisibility(View.GONE);
                    Glide.with(RedditDetailsFragment.this).load(R.drawable.no_image).into(imgThumbnail);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    loading.setVisibility(View.GONE);
                    return false;
                }
            }).into(imgThumbnail);
        }else{
            loading.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.no_image).into(imgThumbnail);
        }


    }
}
