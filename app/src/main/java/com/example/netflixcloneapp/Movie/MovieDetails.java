package com.example.netflixcloneapp.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.Register.NetflixRegister;
import com.example.netflixcloneapp.adapter.CastAdapter;
import com.example.netflixcloneapp.adapter.MovieAdapter;
import com.example.netflixcloneapp.databinding.ActivityMoviedetailsBinding;
import com.example.netflixcloneapp.databinding.NetflixRegisterBinding;
import com.example.netflixcloneapp.login.Netflixlogin;
import com.example.netflixcloneapp.model.Cast;
import com.example.netflixcloneapp.model.Movies;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {

    private ImageView MovieThumnailImg, moviecoverImg;
    private TextView tv_titile, tv_des;
    private FloatingActionButton play_fab;
    private RecyclerView rv_cast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iniView();
        SetupRvCast();

    }

    void iniView() {
        ActivityMoviedetailsBinding binddetails = DataBindingUtil.setContentView(this, R.layout.activity_moviedetails);

        String movieTitle = getIntent().getExtras().getString("title");
        int imgResourceId = getIntent().getExtras().getInt("imgeUrl");
        int imgCover = getIntent().getExtras().getInt("imgCover");

binddetails.playFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetails.this,MovePlayerActivity.class);
                startActivity(intent);
            }
        });
        play_fab = binddetails.playFab;
        rv_cast = binddetails.rvCast;
        MovieThumnailImg = binddetails.detailMovieImg;
        moviecoverImg = binddetails.detailMovieCover;
        tv_titile = binddetails.detailMovieTitle;
        tv_des = binddetails.detailMovieDesc;

        Glide.with(this).load(imgResourceId).into(MovieThumnailImg);
          MovieThumnailImg.setImageResource(imgResourceId);
        Glide.with(this).load(imgCover).into(moviecoverImg);
        // getSupportActionBar().setTitle(movieTitle);
       // moviecoverImg.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_anim));
        // play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_anim));


    }

    void SetupRvCast() {

        List<Cast> cast = new ArrayList<>();
        cast.add(new Cast("willem_dafoe", R.drawable.willem_dafoe));
        cast.add(new Cast("james_franco", R.drawable.james_franco));
        cast.add(new Cast("Joe", R.drawable.joe));
        cast.add(new Cast("kirsten_dunst", R.drawable.kirsten_dunst));

        CastAdapter castAdapter = new CastAdapter(this, cast);

        rv_cast.setAdapter(castAdapter);
        rv_cast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


    }


}