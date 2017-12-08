package com.example.nawarinivad.iakintermid3;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nawarinivad.iakintermid3.data.MovieContract;
import com.example.nawarinivad.iakintermid3.model.Movie;
import com.example.nawarinivad.iakintermid3.utils.NetworkUtils;

import org.json.JSONException;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_title) TextView movieTitle;
    @BindView(R.id.movie_backdrop) ImageView movieBackdrop;
    @BindView(R.id.movie_poster) ImageView moviePoster;
    @BindView(R.id.movie_release_date) TextView movieReleaseDate;
    @BindView(R.id.movie_rating) TextView movieRating;
    private Movie movie;
    public static final String MOVIE_INTENT = "movie.intent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        movie = getIntent().getParcelableExtra(MOVIE_INTENT);
        movieTitle.setText(movie.getTitle());
        movieReleaseDate.setText(movie.getReleaseDate());
        movieRating.setText(String.valueOf(movie.getVoteAverage()));
//        Glide.with(this).load(movie.getPoster_path()).into(moviePoster);
        Glide.with(this)
                .load(NetworkUtils.buildMoviePosterUrl(movie.getPoster_path()))
                .into(moviePoster);

        Glide.with(this)
                .load(NetworkUtils.buildMoviePosterUrl(movie.getBackdropPath()))
                .into(movieBackdrop);
        Log.d("test",movie.getBackdropPath());
    }

    public void save_movie(View v){
        ContentValues contentValues = new ContentValues();

        contentValues.put(MovieContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_POSTER_IMAGE_NAME, movie.getPoster_path());
        contentValues.put(MovieContract.MovieEntry.COLUMN_SYNOPSIS, movie.getTitle());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RATING, movie.getOverview());
        contentValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        // Insert the content values via a ContentResolver
        Uri uri = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, contentValues);

        if(uri != null) {
            Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
