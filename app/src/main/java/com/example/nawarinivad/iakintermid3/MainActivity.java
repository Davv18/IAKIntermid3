package com.example.nawarinivad.iakintermid3;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nawarinivad.iakintermid3.adapter.MovieAdapter;
import com.example.nawarinivad.iakintermid3.data.MovieContract;
import com.example.nawarinivad.iakintermid3.model.Movie;
import com.example.nawarinivad.iakintermid3.utils.NetworkUtils;
import com.example.nawarinivad.iakintermid3.utils.RecyclerViewItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RecyclerViewItemClickListener{

    @BindView(R.id.movie_recyclerview) RecyclerView rvMovies;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter adapter;
    private static final String SORT_BY_POPULAR = "popular";
    private static final String SORT_BY_TOPRATED = "top_rated";
    private static final String SORT_BY_FAVORITE = "favorite";
    private String sort_by;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_fav:
                new getOfflineDataTask().execute();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MovieAdapter(this,movieList,this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        URL url = NetworkUtils.buildUrl("popular");
        new getDataTask().execute(url);
    }

    @Override
    public void onItemClicked(int position) {
//        Toast.makeText(this, "position"+position, Toast.LENGTH_SHORT).show();
        Movie movie = movieList.get(position);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.MOVIE_INTENT,movie);
        startActivity(intent);
    }

    private class getDataTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String results = null;
            try {
                results = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray results = jsonObject.getJSONArray("results");

                for (int i=0; i<results.length(); i++){
                    movieList.add(new Movie(
                            results.getJSONObject(i).getInt("id"),
                            results.getJSONObject(i).getString("title"),
                            results.getJSONObject(i).getString("poster_path"),
                            results.getJSONObject(i).getString("overview"),
                            results.getJSONObject(i).getString("release_date"),
                            results.getJSONObject(i).getDouble("vote_average"),
                            results.getJSONObject(i).getString(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH)
                    ));
                }

                adapter.notifyDataSetChanged();
                Log.e("json",results.toString());
            } catch (Exception e) {
                Log.e("e",e.toString());
                e.printStackTrace();
            }
        }
    }

    private class getOfflineDataTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getDatafromContentProvider();
        }

        private Cursor getDatafromContentProvider(){
            Cursor cursor;
            try {
                cursor = getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            cursor.moveToFirst();
            movieList.clear();
            while (cursor.isAfterLast() == false) {
                movieList.add(new Movie(
                        0,//id
                        cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER_IMAGE_NAME)),
                        cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_SYNOPSIS)),
                        cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE)),
                        cursor.getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RATING)),
                        cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH))
                ));
                cursor.moveToNext();
            }
            cursor.close();
            adapter.notifyDataSetChanged();
        }
    }
}
