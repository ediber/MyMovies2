package com.mymovies2.data;

import io.realm.RealmObject;

public class Selected_User extends RealmObject {
    String movieId;
    String email;

    public Selected_User() {
    }

    public Selected_User(String movieId, String email) {
        this.movieId = movieId;
        this.email = email;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
