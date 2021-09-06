package com.example.netflixcloneapp.MovieInetrface;

import android.widget.ImageView;

import com.example.netflixcloneapp.model.Movies;

public interface MovieClickListener {

    void onMovieClick(Movies movie, ImageView movieImageView);

}
