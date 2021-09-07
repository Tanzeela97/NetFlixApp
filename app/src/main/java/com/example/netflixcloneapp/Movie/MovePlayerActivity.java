package com.example.netflixcloneapp.Movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.inputmethodservice.ExtractEditText;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;

import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.databinding.ActivityMovePlayerBinding;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.hls.DefaultHlsExtractorFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class  MovePlayerActivity extends AppCompatActivity {


    private PlayerView playerView;
    Context context;
    private SimpleExoPlayer simpleExoPlayer;

private  static  final     String Video_test_URl = "http://clips.vorwaerts-gmbh.de/VfE_html5.mp4";
    private  static  final    String Video_test_2="https://ak.picdn.net/shutterstock/videos/9640562/preview/stock-footage-funny-guy-walk-down-the-night-street-city-free-dancing-to-music-in-headphones.webm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_move_player);


        iniExoplayer();


    }

    private void iniExoplayer() {

        ActivityMovePlayerBinding bindMoviePlayer = DataBindingUtil.setContentView(this, R.layout.activity_move_player);
        playerView = bindMoviePlayer.movieExoPlayer;

       // simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();;
        playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "appname"));

        MediaItem firstItem = MediaItem.fromUri(Video_test_URl);
        MediaItem secondItem = MediaItem.fromUri(Video_test_2);
        // Add the media items to be played.



        simpleExoPlayer.addMediaItem(firstItem);
        simpleExoPlayer.addMediaItem(secondItem);


    // Prepare the player.
        simpleExoPlayer.prepare();
// Start the playback.

       // MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(Video_test_URl));
        //simpleExoPlayer.prepare(videoSource );

        simpleExoPlayer.setPlayWhenReady(true);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}