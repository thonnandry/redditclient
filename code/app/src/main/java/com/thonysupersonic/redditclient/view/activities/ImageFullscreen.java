package com.thonysupersonic.redditclient.view.activities;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.thonysupersonic.redditclient.R;

import pl.droidsonroids.gif.GifImageView;

public class ImageFullscreen extends AppCompatActivity {

    String urlImagen = "";
    GifImageView imgThumbnail;

    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fullscreen);

        loading = findViewById(R.id.loading);

        urlImagen = getIntent().getStringExtra("urlImagen");


        if(urlImagen.contains(".gifv")){
            urlImagen = urlImagen.replace(".gifv", ".gif");
        }



        imgThumbnail = findViewById(R.id.imgFullscreen);
        Glide.with(this).load(urlImagen).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                imgThumbnail.setImageResource(R.drawable.no_image);
                loading.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                loading.setVisibility(View.GONE);
                return false;
            }
        }).into(imgThumbnail);

        //GlideApp



    }
}
