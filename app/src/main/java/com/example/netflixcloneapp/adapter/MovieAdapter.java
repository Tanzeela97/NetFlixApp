package com.example.netflixcloneapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflixcloneapp.MovieInetrface.MovieClickListener;
import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.databinding.CastItemBinding;
import com.example.netflixcloneapp.databinding.ItemMovieBinding;
import com.example.netflixcloneapp.model.Movies;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

Context context;
List<Movies> mdata;
 MovieClickListener movieClickListener;

    public MovieAdapter (Context context,List<Movies> mdata,MovieClickListener movieClickListener) {
        this.context = context;
        this.mdata = mdata;
        this.movieClickListener = movieClickListener;


    }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            ItemMovieBinding bindItemMovie= DataBindingUtil.inflate(inflater, R.layout.item_movie, null, false);

           // View view = LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
            return new MyViewHolder(bindItemMovie);


        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


            myViewHolder.TvTitle.setText(mdata.get(i).getTitle());
            myViewHolder.ImgMovie.setImageResource(mdata.get(i).getThumbnail());


        }

        @Override
        public int getItemCount() {
            return mdata.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


            private TextView TvTitle;
            private ImageView ImgMovie;


            public MyViewHolder(@NonNull ItemMovieBinding itemMovieBinding) {

                super(itemMovieBinding.getRoot());
                TvTitle = itemMovieBinding.itemMovieTitle;
                ImgMovie = itemMovieBinding.itemMovieImg;

                itemMovieBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        movieClickListener.onMovieClick(mdata.get(getAdapterPosition()),ImgMovie);


                    }
                });

            }
        }
    }

