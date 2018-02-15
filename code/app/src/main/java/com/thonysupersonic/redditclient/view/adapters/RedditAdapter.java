package com.thonysupersonic.redditclient.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.model.BeRedditRoot;
import com.thonysupersonic.redditclient.view.activities.ImageFullscreen;

import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public class RedditAdapter extends ArrayAdapter<BeRedditRoot> {



    public RedditAdapter(@NonNull Context context, List<BeRedditRoot> items) {
        super(context, android.R.layout.simple_list_item_1, items);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v  = convertView;
        if(v == null){
            //inflating my custom row
            v = LayoutInflater.from(getContext()).inflate(R.layout.row_reddit_list, null);
        }

        TextView lblUpvote =  v.findViewById(R.id.lblUpvote);
        TextView lblDownvote =  v.findViewById(R.id.lblDownvote);
        TextView lblTitle =  v.findViewById(R.id.lblTitle);
        TextView lblCreatedBy =  v.findViewById(R.id.lblCreatedBy);
        ImageView imgThumbnail =  v.findViewById(R.id.imgThumbnail);


        lblUpvote.setText(String.valueOf(getItem(position).data.ups));
        lblDownvote.setText(String.valueOf(getItem(position).data.downs));
        lblTitle.setText(getItem(position).data.title);
        lblCreatedBy.setText(getItem(position).data.author);




        if(!getItem(position).data.thumbnail.contains("http")){
            Glide.with(getContext()).load(R.drawable.no_image).into(imgThumbnail);
            imgThumbnail.setOnClickListener(null);

        }else {
            Glide.with(getContext()).load(getItem(position).data.thumbnail).into(imgThumbnail);


            imgThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iFullscreen = new Intent(getContext(), ImageFullscreen.class);
                    iFullscreen.putExtra("urlImagen", getItem(position).data.url);
                    getContext().startActivity(iFullscreen);
                }
            });

        }


        return v;
    }
}
