package com.example.movielibraryv2.Movie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movielibraryv2.R;
import com.example.movielibraryv2.activities.DetailsActivity;
import com.example.movielibraryv2.activities.EditActivity;
import com.example.movielibraryv2.activities.OverviewActivity;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {

    //StackOverflow
    //https://stackoverflow.com/questions/36862968/android-custom-arrayadapter-with-custom-object
    //https://stackoverflow.com/questions/21161959/custom-arrayadapter-and-onclicklistener-for-a-button-in-a-row

    private static class ViewHolder
    {
        TextView title, Rating, userRating;
        ImageView image;
        CheckBox watched;
    }

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.oa_listview_movie, movies);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        Movie movie = (Movie) getItem(position);
        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.oa_listview_movie, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.listview_item_title);
            viewHolder.Rating = (TextView) convertView.findViewById(R.id.listview_item_rating);
            viewHolder.userRating = (TextView) convertView.findViewById(R.id.listview_item_userrating);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.listview_item_image);
            viewHolder.watched = (CheckBox) convertView.findViewById(R.id.listview_item_watched);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(movie.getTitle());
        viewHolder.Rating.setText(String.valueOf(movie.getRating()));
        viewHolder.userRating.setText(String.valueOf(movie.getUserRating()));
        viewHolder.image.setImageResource(movie.getImage());
        viewHolder.watched.setChecked(movie.isWatched());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(getContext(), DetailsActivity.class);
                details.putExtra(OverviewActivity.MOVIE_DETAILS, (Movie) getItem(position));
                if (getContext() instanceof OverviewActivity)
                {
                    ((OverviewActivity) getContext()).detailsActivityOnClick(details);
                }
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent edit = new Intent(getContext(), EditActivity.class);
                edit.putExtra(OverviewActivity.MOVIE_EDIT, (Movie) getItem(position));
                edit.putExtra(OverviewActivity.MOVIE_POSITION, position);
                if(getContext() instanceof OverviewActivity)
                {
                    ((OverviewActivity) getContext()).editActivityOnLongClick(edit);
                }
                return true;
            }
        });

        return convertView;
    }
}
