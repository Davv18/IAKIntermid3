package com.example.nawarinivad.iakintermid3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nawarinivad.iakintermid3.R;
import com.example.nawarinivad.iakintermid3.model.Movie;
import com.example.nawarinivad.iakintermid3.utils.NetworkUtils;
import com.example.nawarinivad.iakintermid3.utils.RecyclerViewItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nawari Nivad on 12/1/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> moviesList;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public MovieAdapter(Context context, List<Movie> moviesList, RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.context = context;
        this.moviesList = moviesList;
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.movie = moviesList.get(position);
        Glide.with(context)
                .load(NetworkUtils.buildMoviePosterUrl(holder.movie.getPoster_path()))
                .into(holder.movie_poster);

        holder.itemView.setOnClickListener(v -> {
            int itemPosition = holder.getAdapterPosition();
            recyclerViewItemClickListener.onItemClicked(itemPosition);
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_poster) ImageView movie_poster;

        Movie movie;
        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
