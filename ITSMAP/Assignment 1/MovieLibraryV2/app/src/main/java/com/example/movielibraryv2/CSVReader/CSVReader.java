package com.example.movielibraryv2.CSVReader;

import com.example.movielibraryv2.Movie.Movie;
import com.example.movielibraryv2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//StackOverflow
//https://stackoverflow.com/questions/43055661/reading-csv-file-in-android-app

public class CSVReader {
    InputStream inputStream;

    public CSVReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ArrayList<Movie> read()
    {
        ArrayList<Movie> resultList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] split = line.split(";");
                String[] genres = split[2].split(",");
                List<String> genreList = new ArrayList<>();
                Collections.addAll(genreList, genres);

                int movieGenre = getGenre(genreList.get(0));

                Movie movie = new Movie(split[0], split[1], genreList, Double.parseDouble(split[3]), false, movieGenre);
                resultList.add(movie);
            }
        }

        catch (IOException e)
        {
            throw new RuntimeException("Error! Cannot read CSV file: " + e);
        }

        finally
        {
            try
            {
                inputStream.close();
            }

            catch (IOException e)
            {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }

        return resultList;
    }

    private int getGenre(String genre)
    {
        int movieGenre;
        switch (genre.toUpperCase())
        {
            case "ACTION":
                movieGenre = R.drawable.ic_action;
                break;
            case "ADVENTURE":
                movieGenre = R.drawable.ic_adventure;
                break;
            case "ANIMATION":
                movieGenre = R.drawable.ic_animation;
                break;
            case "BIOGRAPHY":
                movieGenre = R.drawable.ic_biography;
                break;
            case "COMEDY":
                movieGenre = R.drawable.ic_comedy;
                break;
            case "DRAMA":
                movieGenre = R.drawable.ic_drama;
                break;
            case "FAMILY":
                movieGenre = R.drawable.ic_family;
                break;
            case "HISTORY":
                movieGenre = R.drawable.ic_history;
                break;
            case "HORROR":
                movieGenre = R.drawable.ic_horror;
                break;
            case "MUSIC":
                movieGenre = R.drawable.ic_music;
                break;
            case "ROMANCE":
                movieGenre = R.drawable.ic_romance;
                break;
            case "SCI-FI":
                movieGenre = R.drawable.ic_sci_fi;
                break;
            case "SPORT":
                movieGenre = R.drawable.ic_sport;
                break;
            default:
                movieGenre = R.drawable.ic_patrick;
                break;
        }

        return movieGenre;
    }

}
