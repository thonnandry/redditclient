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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.thonysupersonic.redditclient.R;
import com.thonysupersonic.redditclient.model.BeRedditRoot;
import com.thonysupersonic.redditclient.view.activities.ImageFullscreen;
import com.thonysupersonic.redditclient.view.utilities.Functions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthony on 2/15/18.
 */

public class RedditAdapter extends ArrayAdapter<BeRedditRoot> {


    ArrayList viewed;

    public RedditAdapter(@NonNull Context context, List<BeRedditRoot> items, ArrayList<String> viewed) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.viewed = viewed;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v  = convertView;
        RedditAdapterHolder holder;

        if(v == null){
            //inflating my custom row
            v = LayoutInflater.from(getContext()).inflate(R.layout.row_reddit_list, null);
            holder = new RedditAdapterHolder(v);
            v.setTag(holder);
        }


        holder = (RedditAdapterHolder) v.getTag();


        holder.lblUpvote.setText(String.valueOf(getItem(position).data.ups));
        holder.lblDownvote.setText(String.valueOf(getItem(position).data.downs));
        holder.lblTitle.setText(getItem(position).data.title);
        holder.lblCreatedBy.setText("By " + getItem(position).data.author + " - " + Functions.convertUTCTime(getItem(position).data.created));
        holder.lblComments.setText(String.valueOf(getItem(position).data.num_comments));



        if(!getItem(position).data.thumbnail.contains("http")){
            Glide.with(getContext()).load(R.drawable.no_image).into(holder.imgThumbnail);
            holder.imgThumbnail.setOnClickListener(null);

        }else {
            Glide.with(getContext()).load(getItem(position).data.thumbnail).into(holder.imgThumbnail);


            holder.imgThumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iFullscreen = new Intent(getContext(), ImageFullscreen.class);
                    iFullscreen.putExtra("urlImagen", getItem(position).data.url);
                    getContext().startActivity(iFullscreen);
                }
            });

        }

        //check if its already seen
        if(viewed.contains(getItem(position).data.name)){
            v.setAlpha(0.5f);
        }else{
            v.setAlpha(1f);
        }


        return v;
    }

    public class RedditAdapterHolder{

        TextView lblUpvote;
        TextView lblDownvote;
        TextView lblTitle;
        TextView lblCreatedBy;
        TextView lblComments;
        ImageView imgThumbnail;


        public RedditAdapterHolder(View v){
             lblUpvote =  v.findViewById(R.id.lblUpvote);
             lblDownvote =  v.findViewById(R.id.lblDownvote);
             lblTitle =  v.findViewById(R.id.lblTitle);
             lblCreatedBy =  v.findViewById(R.id.lblCreatedBy);
             lblComments =  v.findViewById(R.id.lblComments);
             imgThumbnail =  v.findViewById(R.id.imgThumbnail);


        }

    }
}
