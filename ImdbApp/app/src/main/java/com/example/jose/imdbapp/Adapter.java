package com.example.jose.imdbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jose.imdbapp.Utils.ImageLoader;

import java.util.List;

/**
 * Created by jose on 4/10/2015.
 */
public class Adapter extends ArrayAdapter<MovieInfo>{

    private Context context;
    private int resource;
    private List<MovieInfo> movieList;
    public ImageLoader imageLoader;

    public Adapter(Context context, int resource, List<MovieInfo> movieList) {
        super(context, resource, movieList);

        this.context = context;
        this.resource = resource;
        this.movieList = movieList;

        imageLoader = new ImageLoader(context.getApplicationContext());
    }


    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }
    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class Holder{

        public TextView textReleaseDate;
        public TextView txtFilmName;
        public TextView textRating;
        public ImageView filmPoster;
        public RatingBar fiveStarRating;
        public RatingBar singleStarRating;
        public TextView  voteCount;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View vi=convertView;
        // ViewHolder holder;


        Holder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);

            holder = new Holder();
            holder.txtFilmName = (TextView) convertView.findViewById(R.id.txtFilmName);
            holder.textReleaseDate = (TextView) convertView.findViewById(R.id.txtReleaseDate);
            holder.fiveStarRating = (RatingBar) convertView.findViewById(R.id.fiveStarRating);
            holder.filmPoster = (ImageView) convertView.findViewById(R.id.poster);
            holder.voteCount = (TextView) convertView.findViewById(R.id.txtRating);

            convertView.setTag(holder);

        } else {

            holder = (Holder) convertView.getTag();
        }

        MovieInfo getMovieInfo = movieList.get(position);

        holder.txtFilmName.setText(getMovieInfo.getFilmName());
        holder.textReleaseDate.setText(getMovieInfo.getReleaseDate());
        holder.fiveStarRating.setRating(Float.parseFloat(getMovieInfo.getVoteAvg()) / 2);
        if (getMovieInfo.getPosterPath().equals("null")) {
            holder.filmPoster.setImageResource(R.drawable.film);
        } else {
            imageLoader.DisplayImage("http://image.tmdb.org/t/p/w500" + getMovieInfo.getPosterPath(), holder.filmPoster);
        }
        holder.voteCount.setText("(" + getMovieInfo.getVoteAvg() + "/10) voted by " + getMovieInfo.getVoteCount() + " users");

        return convertView;

    }


}
