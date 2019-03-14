package com.example.movielibraryv2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movielibraryv2.Movie.Movie;
import com.example.movielibraryv2.R;

public class DetailsActivity extends AppCompatActivity {

    //Declarations on layout
    Button DAbtnOk;
    ImageView DAimage;
    TextView DAtitle, DAplot, DAgenre, DArating, DAuserRating, DAcomment;
    CheckBox DAwatched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Get intent
        Movie movie = getIntent().getExtras().getParcelable(OverviewActivity.MOVIE_DETAILS);

        //Find by Id
        DAbtnOk = findViewById(R.id.DA_btnOk);
        DAimage = findViewById(R.id.DA_imageview_genres);
        DAtitle = findViewById(R.id.DA_txtTitle);
        DAplot = findViewById(R.id.DA_txtPlot);
        DAgenre = findViewById(R.id.DA_txtGenres_text);
        DArating = findViewById(R.id.DA_txtRating_value);
        DAuserRating = findViewById(R.id.DA_txtUserRating_value);
        DAcomment = findViewById(R.id.DA_txtComment_text);
        DAwatched = findViewById(R.id.EA_checkbox_watched);



        //Button OK
        DAbtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToMain(v);
            }
        });

        //Gets and sets the data
        DAtitle.setText(movie.getTitle());
        DAimage.setImageResource(movie.getImage());
        DAplot.setText(movie.getPlot());
        DArating.setText(String.valueOf(movie.getRating()));
        DAuserRating.setText(String.valueOf(movie.getUserRating()));
        DAcomment.setText(movie.getComment());
        DAwatched.setChecked(movie.isWatched());

        //Genres
        StringBuilder splitGenre = new StringBuilder();
        if ((movie != null ? movie.getGenres() : null) != null) {
            for (String genre : movie.getGenres())
            {
                splitGenre.append(genre).append(", ");
            }
        }
        DAgenre.setText(splitGenre.toString());

    }

    //Returns to Overview Activity
    private void BackToMain(View v)
    {
        Intent intent = new Intent(this, OverviewActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }
}
