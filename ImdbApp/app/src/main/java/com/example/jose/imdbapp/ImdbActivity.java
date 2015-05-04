package com.example.jose.imdbapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ImdbActivity extends ActionBarActivity  {

    Adapter adapter;
    ListView lstView;
    private ProgressDialog pDialog;
    JSONArray results = null;
    private List<MovieInfo> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb);
        getSupportActionBar();
        movieList = new ArrayList<MovieInfo>();
       adapter = new Adapter(this.getBaseContext(),R.layout.most_popular_row, movieList);
       lstView=(ListView) findViewById(R.id.listView);
       GetMovies gtMvs = new GetMovies(ImdbActivity.this);
       gtMvs.execute("http://api.themoviedb.org/3/movie/popular?api_key=8496be0b2149805afa458ab8ec27560c");
       lstView.setAdapter(adapter);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
        //-----
        // Item Click Listener for the listview
        //OnItemClickListener itemClickListener = new OnItemClickListener()
       /* AdapterView.OnItemClickListener   itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        };
        /*{
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                // Getting the Container Layout of the ListView
                LinearLayout linearLayoutParent = (LinearLayout) container;

                // Getting the inner Linear Layout
                LinearLayout linearLayoutChild = (LinearLayout ) linearLayoutParent.getChildAt(1);

                // Getting the Country TextView
                TextView tvCountry = (TextView) linearLayoutChild.getChildAt(0);

                Toast.makeText(getBaseContext(), tvCountry.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        };

        // Setting the item click listener for the listview
        lstView.setOnItemClickListener(itemClickListener);
        //-----
      /* lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });*/

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
            case R.id.upcomingmovies:
                //Toast.makeText(getApplicationContext(), "Clicked on Most Popular", Toast.LENGTH_LONG).show();

                GetMovies gtMvsUpcming = new GetMovies(ImdbActivity.this);
                gtMvsUpcming.execute("http://api.themoviedb.org/3/movie/upcoming?api_key=8496be0b2149805afa458ab8ec27560c");
                break;
            case R.id.toprated:


                GetMovies gtMvsTopRated = new GetMovies(ImdbActivity.this);
                gtMvsTopRated.execute("http://api.themoviedb.org/3/movie/top_rated?api_key=f47dd4de64c6ef630c2b0d50a087cc33");
                break;
            case R.id.nowplaying:
                GetMovies gtMvsNowPlyng = new GetMovies(ImdbActivity.this);
                gtMvsNowPlyng.execute("http://api.themoviedb.org/3/movie/now_playing?api_key=f47dd4de64c6ef630c2b0d50a087cc33");
                break;
            case R.id.latestmovies:
                GetMovies gtMvsLatestMovies = new GetMovies(ImdbActivity.this);
                gtMvsLatestMovies.execute("http://api.themoviedb.org/3/movie/popular?api_key=8496be0b2149805afa458ab8ec27560c");
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
   private class GetMovies extends AsyncTask<String ,Void,List<MovieInfo>> {

        Context context;
        private GetMovies(Context context) {
        this.context=context;
        }

        @Override
        protected List<MovieInfo> doInBackground(String... url) {
            // Creating service handler class instance
            movieList=new ArrayList<MovieInfo>();

            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url[0], ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    results = jsonObj.getJSONArray(Constants.TAG_RESULTS);

                    // looping through All Contacts
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject c = results.getJSONObject(i);
                        MovieInfo movieDetails=new MovieInfo();
                        movieDetails.setFilmName(c.getString(Constants.TAG_ORIGINAL_TITLE));
                        movieDetails.setReleaseDate(c.getString(Constants.TAG_RELEASE_DATE));
                        movieDetails.setPosterPath(c.getString(Constants.TAG_POSTER_PATH));
                        movieDetails.setVoteAvg(c.getString(Constants.TAG_VOTE_AVERAGE));
                        movieDetails.setVoteCount(c.getString(Constants.TAG_VOTE_COUNT));
                        movieDetails.setId(c.getString(Constants.TAG_ID));
                        movieList.add(movieDetails);
                    }               } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return movieList;
        }
        @Override
        protected void onPostExecute(List<MovieInfo> result) {
            super.onPostExecute(result);
            if (result == null) {

                Toast.makeText(context, "Unable to fetch data from server", Toast.LENGTH_LONG).show();
            }
            else {

                Adapter setAdpter = new Adapter(ImdbActivity.this,R.layout.most_popular_row,result);
                lstView.setAdapter(setAdpter);
            }
        }

    }
}
