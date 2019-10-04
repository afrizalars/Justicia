package com.example.muvi.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieModel implements Parcelable {
    private String title;
    private String duration;
    private String release;
    private String overview;
    private String rating;
    private String poster;

    public MovieModel(JSONObject object) {
        try {
            String title = object.getString("title");
            String release_date = object.getString("release_date");
            String overview = object.getString("overview");
            String poster = "https://image.tmdb.org/t/p/w342" + object.getString("poster_path");
            String rating = object.getString("vote_average");

            this.title = title;
            this.poster = poster;
            this.release = release_date;
            this.overview = overview;
            this.rating = rating;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.duration);
        dest.writeString(this.release);
        dest.writeString(this.poster);
        dest.writeString(this.rating);
        dest.writeString(this.overview);
    }

    public MovieModel() {
    }

    protected MovieModel(Parcel in) {
        this.title = in.readString();
        this.duration = in.readString();
        this.release = in.readString();
        this.poster = in.readString();
        this.rating = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
}
