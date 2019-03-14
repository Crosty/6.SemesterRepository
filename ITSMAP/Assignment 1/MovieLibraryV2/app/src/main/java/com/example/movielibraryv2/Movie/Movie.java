package com.example.movielibraryv2.Movie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Movie implements Parcelable {
    private String Title;
    private String Plot;
    private List<String> Genres;
    private double Rating;
    private double UserRating;
    private boolean Watched;
    private int Image;
    private String Comment;

    public Movie(String title, String plot, List<String> genres, double rating, double userRating, boolean watched, int image, String comment) {
        Title = title;
        Plot = plot;
        Genres = genres;
        Rating = rating;
        UserRating = userRating;
        Watched = watched;
        Image = image;
        Comment = comment;
    }

    public Movie(String title, String plot, List<String> genres, double rating, boolean watched, int image)
    {
        Title = title;
        Plot = plot;
        Genres = genres;
        Rating = rating;
        Watched = watched;
        Image = image;

        UserRating = 0;
        Comment = "";
    }

    protected Movie(Parcel in) {
        this.Title = in.readString();
        this.Plot = in.readString();
        this.Genres = in.createStringArrayList();
        this.Rating = in.readDouble();
        this.UserRating = in.readDouble();
        this.Watched = in.readByte() != 0;
        this.Image = in.readInt();
        this.Comment = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeString(this.Plot);
        dest.writeStringList(this.Genres);
        dest.writeDouble(this.Rating);
        dest.writeDouble(this.UserRating);
        dest.writeByte((byte) (this.Watched ? 1 : 0));
        dest.writeInt(this.Image);
        dest.writeString(this.Comment);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public List<String> getGenres() {
        return Genres;
    }

    public void setGenres(List<String> genres) {
        Genres = genres;
    }

    public double getRating() {
        return Rating;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public double getUserRating() {
        return UserRating;
    }

    public void setUserRating(double userRating) {
        UserRating = userRating;
    }

    public boolean isWatched() {
        return Watched;
    }

    public void setWatched(boolean watched) {
        Watched = watched;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }


}
