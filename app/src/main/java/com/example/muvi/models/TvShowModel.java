package com.example.muvi.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class TvShowModel implements Parcelable {
    private String poster;
    private String title;
    private String overview;
    private String release;
    private String rating;

    public TvShowModel (JSONObject object) {
        try {
            String title = object.getString("name");
            String release_date = object.getString("first_air_date");
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

    public TvShowModel() {

    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release);
        dest.writeString(this.rating);
    }

    protected TvShowModel(Parcel in) {
        this.poster = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.release = in.readString();
        this.rating = in.readString();
    }

    public static final Parcelable.Creator<TvShowModel> CREATOR = new Parcelable.Creator<TvShowModel>() {
        @Override
        public TvShowModel createFromParcel(Parcel source) {
            return new TvShowModel(source);
        }

        @Override
        public TvShowModel[] newArray(int size) {
            return new TvShowModel[size];
        }
    };
}
