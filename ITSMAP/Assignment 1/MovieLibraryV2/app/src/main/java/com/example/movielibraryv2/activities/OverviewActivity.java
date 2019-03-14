package com.example.movielibraryv2.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.movielibraryv2.CSVReader.CSVReader;
import com.example.movielibraryv2.Movie.Movie;
import com.example.movielibraryv2.Movie.MovieAdapter;
import com.example.movielibraryv2.R;

import java.io.InputStream;
import java.util.ArrayList;

public class OverviewActivity extends AppCompatActivity {

    //Declarations on layout
    Button OAbtnExit;
    ListView OAlistview_movies;

    //Request table
    public final static int REQUEST_DETAILS = 101;
    public final static int REQUEST_EDIT = 102;
    public final static String MOVIE_DETAILS = "movie_details";
    public final static String MOVIE_EDIT = "movie_edit";
    public final static String MOVIE_POSITION = "movie_position";
    public final static String REQUEST_LIST = "movie_list";

    //Adapter + CSV reader
    MovieAdapter movieAdapter;
    CSVReader reader;
    ArrayList<Movie> movieArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find by Id
        OAbtnExit = findViewById(R.id.OAbtn_exit);
        OAlistview_movies = findViewById(R.id.OAlistview_movies);

        //Saved instance state + load CSV file
        if (savedInstanceState == null)
        {
            InputStream inputStream = this.getResources().openRawResource(R.raw.movielist);
            reader = new CSVReader(inputStream);
            movieArrayList = reader.read();
        }
        else
        {
            movieArrayList = savedInstanceState.getParcelableArrayList(REQUEST_LIST);
        }

        //Exit button
        OAbtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Listview of Movies
        movieAdapter = new MovieAdapter(this, movieArrayList);
        OAlistview_movies.setAdapter(movieAdapter);
    }

    //Saved instance state
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(REQUEST_LIST, movieArrayList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode)
        {
            case REQUEST_EDIT:
                switch (resultCode)
                {
                    case RESULT_OK: {
                        Movie movie = (Movie) data.getExtras().getParcelable(MOVIE_DETAILS);
                        int position = (int) data.getExtras().getInt(MOVIE_POSITION);
                        movieAdapter.remove(movieAdapter.getItem(position));
                        movieAdapter.insert(movie, position);
                        Toast.makeText(this, "Edited!", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case RESULT_CANCELED: {
                        Toast.makeText(this, "Cancelled!", Toast.LENGTH_SHORT).show();
                    }
                }
            default:
                break;
        }
    }

    //DetailsActivity
    public void detailsActivityOnClick(Intent intent)
    {
        startActivityForResult(intent, REQUEST_DETAILS);
    }

    //EditActivity
    public void editActivityOnLongClick(Intent intent)
    {
        startActivityForResult(intent, REQUEST_EDIT);
    }
}
