package com.example.nawarinivad.iakintermid3.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nawarinivad.iakintermid3.model.Movie;

/**
 * Created by Nawari Nivad on 12/8/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final short VERSION = 1;
    private static final String DATABASE_NAME = "movie.db";

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = " CREATE TABLE " +
                MovieContract.MovieEntry.TABLE_NAME + "(" +
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_POSTER_IMAGE_NAME + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_SYNOPSIS + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_RATING + " FLOAT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL " +
                ");";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
