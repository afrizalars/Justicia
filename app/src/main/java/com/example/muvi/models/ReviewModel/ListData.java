package com.example.muvi.models.ReviewModel;

public class ListData {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_lawyer() {
        return id_lawyer;
    }

    public void setId_lawyer(String id_lawyer) {
        this.id_lawyer = id_lawyer;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    String id_lawyer;
    String review;
    Integer rating;
}
