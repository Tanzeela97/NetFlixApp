package com.example.netflixcloneapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.databinding.ActivitySplashBinding;
import com.example.netflixcloneapp.login.Netflixlogin;


public class MainActivity extends AppCompatActivity {

    //set duration of splash screen
    private static final int SPLASH = 3300;


    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
//        Intent intent = new Intent(MainActivity.this, Netflixlogin.class);
//        startActivity(intent);
//        finish();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Netflixlogin.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH);

    }
}