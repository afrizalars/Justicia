package com.example.muvi.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FavoriteTvModel extends RealmObject implements Parcelable {

    @PrimaryKey
    private Integer id;
    private String title;
    private String poster;
    private String rating;
    private String sinopsis;
    private String release;
    private String isMovie;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
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

    public String getIsMovie() {
        return isMovie;
    }

    public void setIsMovie(String isMovie) {
        this.isMovie = isMovie;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster);
        dest.writeString(this.rating);
        dest.writeString(this.sinopsis);
        dest.writeString(this.release);
        dest.writeString(this.isMovie);
    }

    public FavoriteTvModel() {
    }

    protected FavoriteTvModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.poster = in.readString();
        this.rating = in.readString();
        this.sinopsis = in.readString();
        this.release = in.readString();
        this.isMovie = in.readString();
    }

    public static final Parcelable.Creator<FavoriteTvModel> CREATOR = new Parcelable.Creator<FavoriteTvModel>() {
        @Override
        public FavoriteTvModel createFromParcel(Parcel source) {
            return new FavoriteTvModel(source);
        }

        @Override
        public FavoriteTvModel[] newArray(int size) {
            return new FavoriteTvModel[size];
        }
    };
}
