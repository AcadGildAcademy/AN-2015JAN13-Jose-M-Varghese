package com.example.jose.imdbapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ImdbActivity extends ActionBarActivity {

    Adapter adapter;
    ListView lstView;
    private ProgressDialog pDialog;
    // Base URL to get  JSON
    private static String url = "http://api.themoviedb.org/";
    private static String API_VERSION="3";
    private static final String TAG_RESULTS="results";
// JSON Node names
    private static final String TAG_ADULT="adult";
    private static final String TAG_BACKDROP_PATH="backdrop_path";
    private static final String TAG_ID="id";
    private static final String TAG_ORIGINAL_TITLE="original_title";
    private static final String TAG_RELEASE_DATE="release_date";
    private static final String TAG_POSTER_PATH="poster_path";
    private static final String TAG_POPULARITY="popularity";
    private static final String TAG_TITLE="title";
    private static final String TAG_VIDEO="video";
    private static final String TAG_VOTE_AVERAGE="vote_average";
    private static final String TAG_VOTE_COUNT="vote_count";
    JSONArray results = null;
    private List<MovieInfo> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb);
        getSupportActionBar();
        movieList = new ArrayList<>();
       adapter = new Adapter(this,R.layout.most_popular_row, movieList);
       lstView=(ListView) findViewById(R.id.listView);
       lstView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_imdb, menu);
       // getMenuInflater().inflate(R.menu.imdb,menu);
        //getMenuInflater().inflate(R.menu.optionsmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.mostpopular:
                //Toast.makeText(getApplicationContext(), "Clicked on Most Popular", Toast.LENGTH_LONG).show();
                GetMovies gtMvs = new GetMovies(ImdbActivity.this);
                gtMvs.execute("http://api.themoviedb.org/3/movie/popular?api_key=8496be0b2149805afa458ab8ec27560c");
                break;

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Async task class to get json by making HTTP call
     * */
   private class GetMovies extends AsyncTask<String ,Void,MovieInfo> {

        Context context;
        private GetMovies(Context context) {
            this.context = context;
        }

        @Override
        protected MovieInfo doInBackground(String... url) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url[0], ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    results = jsonObj.getJSONArray(TAG_RESULTS);

                    // looping through All Contacts
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject c = results.getJSONObject(i);
                        MovieInfo movieDetails=new MovieInfo();
                        movieDetails.setFilmName(c.getString(TAG_ORIGINAL_TITLE));
                        movieDetails.setReleaseDate(c.getString(TAG_RELEASE_DATE));
                        movieDetails.setPosterPath(c.getString(TAG_POSTER_PATH));
                        movieDetails.setVoteAvg(c.getString(TAG_VOTE_AVERAGE));
                        movieDetails.setVoteCount(c.getString(TAG_VOTE_COUNT));
                        // adding contact to contact list
                        movieList.add(movieDetails);
                    }               } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }
        @Override
        protected void onPostExecute(MovieInfo result) {
            super.onPostExecute(result);
            if (result == null) {

                Toast.makeText(context, "Unable to fetch data from server", Toast.LENGTH_LONG).show();
            }
        }

    }
}
