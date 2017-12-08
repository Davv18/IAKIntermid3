package com.example.nawarinivad.iakintermid3.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Nawari Nivad on 12/1/2017.
 */

public class NetworkUtils {
    final static private String MOVIEDB_BASE_URL = "https://api.themoviedb.org/3/movie";
    final static private String MOVIE_POSTER_BASE_URL = "https://image.tmdb.org/t/p/";

    final static private String PARAM_API_KEY = "api_key";
    final static private String API_KEY = "7a4b66f3010665fe9ee0b9a358f74f30";
    final static private String IMAGE_SIZE = "w185";
    final static private String VIDEOS_ENDPOINT_SEGMENT = "videos";
    final static private String REVIEWS_ENDPOINT_SEGMENT = "reviews";


    public static boolean isOnline(ConnectivityManager cm) {
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    public static URL buildUrl(String sort_by) {
        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(sort_by)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static URL buildMoviePosterUrl(String poster_image_name) {
        poster_image_name = poster_image_name.startsWith("/") ? poster_image_name.substring(1) : poster_image_name;

        Uri builtUri = Uri.parse(MOVIE_POSTER_BASE_URL).buildUpon()
                .appendPath(IMAGE_SIZE)
                .appendPath(poster_image_name)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static URL buildMovieVideoUrl(String movie_id) {
        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(movie_id)
                .appendPath(VIDEOS_ENDPOINT_SEGMENT)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static URL buildMovieReviewUrl(String movie_id) {
        Uri builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(movie_id)
                .appendPath(REVIEWS_ENDPOINT_SEGMENT)
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

