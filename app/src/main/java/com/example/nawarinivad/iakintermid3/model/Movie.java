package com.example.nawarinivad.iakintermid3.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nawari Nivad on 12/1/2017.
 */

public class Movie implements Parcelable{

    private int id;
    private String title;
    private float popularity;
    private String poster_path;
    private String backdropPath;
    private String overview;
    private String releaseDate;
    private int voteCount;
    private double voteAverage;

    public Movie(String posterPath) {
        this.poster_path = posterPath;
    }

    public Movie(int id, String title, String poster_path, String overview, String releaseDate, double voteAverage, String backdropPath) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.backdropPath = backdropPath;
    }

    public Movie(int id, String title, float popularity, String posterPath, String backdropPath, String overview, String releaseDate, int voteCount, float voteAverage) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.poster_path = posterPath;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        popularity = in.readFloat();
        poster_path = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        voteCount = in.readInt();
        voteAverage = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeFloat(popularity);
        dest.writeString(poster_path);
        dest.writeString(backdropPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeInt(voteCount);
        dest.writeDouble(voteAverage);
    }
}
