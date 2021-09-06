package com.example.netflixcloneapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.databinding.CastItemBinding;
import com.example.netflixcloneapp.model.Cast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    Context context;
    List<Cast> mdata;

    public CastAdapter(Context context, List<Cast> mdata) {
        this.context = context;
        this.mdata = mdata;
    }


    public class CastViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CastViewHolder(@NonNull @NotNull CastItemBinding castItemBinding) {
            super(castItemBinding.getRoot());
            imageView = castItemBinding.itemCastImg;
        }
    }


    @Override
    public CastViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CastItemBinding bindCast= DataBindingUtil.inflate(inflater, R.layout.cast_item, null, false);

        //        View view = LayoutInflater.from(context).inflate(R.layout.cast_item, parent, false);
        return new CastViewHolder(bindCast);
    }

    @Override
    public void onBindViewHolder(CastViewHolder holder, int position) {
        Glide.with(context).load(mdata.get(position).getImg_Link()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
}

