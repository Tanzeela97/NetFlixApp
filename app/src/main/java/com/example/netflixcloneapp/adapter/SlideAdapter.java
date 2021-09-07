package com.example.netflixcloneapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.example.netflixcloneapp.Movie.MovePlayerActivity;
import com.example.netflixcloneapp.Movie.MovieDetails;
import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.databinding.SlideItemBinding;
import com.example.netflixcloneapp.model.Slide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlideAdapter extends PagerAdapter {

    private Context mContext;
    private List<Slide> mList;
    SlideItemBinding bindslide;

    public SlideAdapter(Context mContext, List<Slide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //data binding
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        bindslide = DataBindingUtil.inflate(inflater, R.layout.slide_item, null, false);

        ImageView slideImg = bindslide.slideImg;
        TextView slideText = bindslide.slideTitle;
        slideImg.setImageResource(mList.get(position).getImage());
        slideText.setText(mList.get(position).getTitle());

        container.addView(bindslide.getRoot());
//
        bindslide.floatPointActionBg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MovePlayerActivity.class);
                mContext.startActivity(intent);
            }
        });

        return bindslide.getRoot();
    }

    @Override
    public int getCount() {
        return mList.size();
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
