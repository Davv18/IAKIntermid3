package com.example.nawarinivad.iakintermid3.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Nawari Nivad on 12/8/2017.
 */

public class MovieContract {

    private MovieContract() { }

    public static final String AUTHORITY = "com.example.nawarinivad.iakintermid3";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class MovieEntry implements BaseColumns{

        public static final String PATH_MOVIES = "movies";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_TITLE = "original_title";
        public static final String COLUMN_POSTER_IMAGE_NAME = "poster_path";
        public static final String COLUMN_SYNOPSIS = "overview";
        public static final String COLUMN_RATING = "vote_average";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
    }

}
