package com.example.movielibraryv2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.movielibraryv2.Movie.Movie;
import com.example.movielibraryv2.R;

public class EditActivity extends AppCompatActivity {

    //Declarations on layout
    Button btnCancel, btnOk;
    TextView EAtitle, EAuserRating;
    SeekBar EAseekbarUserrating;
    CheckBox EAwatched;
    EditText EAcomment;

    //Properties
    private Movie movie;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Get intent
        movie = getIntent().getExtras().getParcelable(OverviewActivity.MOVIE_EDIT);
        position = getIntent().getExtras().getInt(OverviewActivity.MOVIE_POSITION);

        //Find by Id
        btnCancel = findViewById(R.id.EA_btnCancel);
        btnOk = findViewById(R.id.EA_btnOk);
        EAtitle = findViewById(R.id.EA_txtTitle);
        EAuserRating = findViewById(R.id.EA_txtUserRating_value);
        EAseekbarUserrating = findViewById(R.id.EA_seekbar_userrating);
        EAwatched = findViewById(R.id.EA_checkbox_watched);
        EAcomment = findViewById(R.id.EA_edittext_comment);

        //Gets and sets the data
        EAtitle.setText(movie.getTitle());
        EAuserRating.setText(Double.toString(movie.getUserRating()));
        EAseekbarUserrating.setProgress((int) movie.getUserRating());
        EAcomment.setText(movie.getComment());
        EAwatched.setChecked(movie.isWatched());

        //Seekbar user rating
        EAseekbarUserrating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double ur = progress/10.0;
                EAuserRating.setText(Double.toString(ur));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Button Exit
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(EditActivity.this, OverviewActivity.class);
                setResult(RESULT_CANCELED, cancel);
                finish();
            }
        });

        //Button OK
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBtnOk(v);
            }
        });
    }

    private void setBtnOk(View v)
    {
        movie.setWatched(EAwatched.isChecked());
        movie.setComment(EAcomment.getText().toString());
        movie.setUserRating(Double.parseDouble(EAuserRating.getText().toString()));
        Intent edit = new Intent(this, OverviewActivity.class);
        edit.putExtra(OverviewActivity.MOVIE_DETAILS, (Movie) movie);
        edit.putExtra(OverviewActivity.MOVIE_POSITION, position);
        setResult(RESULT_OK, edit);
        finish();
    }
}
