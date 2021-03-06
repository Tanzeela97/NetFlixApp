package com.example.netflixcloneapp.utils;

import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.model.Movies;
import com.example.netflixcloneapp.model.Slide;
import com.google.android.exoplayer2.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
//static List<Slide> lstSlides;

    public static List<Slide> getSlider() {
        // prepare a list of slides ..
        List<Slide> lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide1, "Movies \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Movies \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide4, "Movies \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Movies \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide3, "Movies \nmore text here"));
        lstSlides.add(new Slide(R.drawable.slide4, "Movies \nmore text here"));

        return lstSlides;
    }
//


    public static List<Movies> getPopularMovie() {

        List<Movies> lstMovies = new ArrayList<>();
        lstMovies.add(new Movies("Moana", R.drawable.moana, R.drawable.moana));
        lstMovies.add(new Movies("Black P", R.drawable.blackp, R.drawable.spider));
        lstMovies.add(new Movies("The Cinderalla", R.drawable.mov2));
        lstMovies.add(new Movies("The Martian", R.drawable.martian));
        lstMovies.add(new Movies("The Martian", R.drawable.martian));
        lstMovies.add(new Movies("The Martian", R.drawable.martian));

        return lstMovies;


    }
}