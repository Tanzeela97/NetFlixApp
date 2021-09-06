package com.example.netflixcloneapp.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.inputmethodservice.ExtractEditText;
import android.net.Uri;
import android.os.Bundle;

import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.databinding.ActivityMovePlayerBinding;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.DefaultHlsExtractorFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class  MovePlayerActivity extends AppCompatActivity {


    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    public static final String Video_test_URl = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_move_player);


        iniExoplayer();


    }

    private void iniExoplayer() {

        ActivityMovePlayerBinding bindMoviePlayer = DataBindingUtil.setContentView(this, R.layout.activity_move_player);
        playerView = bindMoviePlayer.movieExoPlayer;
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
      playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "appname"));
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(Video_test_URl));

        simpleExoPlayer.prepare(videoSource);

        simpleExoPlayer.setPlayWhenReady(true);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}