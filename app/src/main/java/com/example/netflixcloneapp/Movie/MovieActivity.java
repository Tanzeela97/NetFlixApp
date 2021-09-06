package com.example.netflixcloneapp.Movie;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.netflixcloneapp.MovieInetrface.MovieClickListener;
import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.Register.NetflixRegister;
import com.example.netflixcloneapp.adapter.MovieAdapter;
import com.example.netflixcloneapp.adapter.SlideAdapter;
import com.example.netflixcloneapp.databinding.ActivityMovieBinding;
import com.example.netflixcloneapp.databinding.ActivityMoviedetailsBinding;
import com.example.netflixcloneapp.login.Netflixlogin;
import com.example.netflixcloneapp.model.Movies;
import com.example.netflixcloneapp.model.Slide;
import com.example.netflixcloneapp.utils.DataSource;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MovieActivity extends AppCompatActivity implements MovieClickListener {

    private List<Slide> lstSlides;

    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView MoviesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           Slider();
        initMovie();


    }


    public void Slider() {
        //slider
        ActivityMovieBinding bindMovieAct = DataBindingUtil.setContentView(this, R.layout.activity_movie);
         Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MovieActivity.SliderTimer(), 4000, 6000);
        indicator = bindMovieAct.indicator;

        SlideAdapter adapter = new SlideAdapter(this, DataSource.getSlider());
        bindMovieAct.sliderPager.setAdapter(adapter);
        sliderPager = bindMovieAct.sliderPager;
        // setup timer
        bindMovieAct.indicator.setupWithViewPager(sliderPager, true);
    }
    // Recyclerview Setup
    // ini data

    //movie slider
    public void initMovie() {
        ActivityMovieBinding bindMovieAct = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        MoviesRV = bindMovieAct.RvMovies;
        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovie(), this);
        bindMovieAct.RvMovies.setAdapter(movieAdapter);
        bindMovieAct.RvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    public void onMovieClick(Movies movie, ImageView movieImageView) {
        // here we send movie information to detail activity
        // also we ll create the transition animation between the two activity

        Intent intent = new Intent(this, MovieDetails.class);
        // send movie information to deatilActivity
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        // lets crezte the animation


        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieActivity.this,
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());


        // i l make a simple test to see if the click works

        Toast.makeText(this, "item clicked : " + movie.getTitle(), Toast.LENGTH_LONG).show();
        // it works great


    }


    class SliderTimer extends TimerTask {


        @Override
        public void run() {

            MovieActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem() < DataSource.getSlider().size()-1 ) {
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                    } else
                        sliderPager.setCurrentItem(0);
                }
            });


        }
    }

}

