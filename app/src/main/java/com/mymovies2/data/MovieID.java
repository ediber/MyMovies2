package com.mymovies2.data;

import io.realm.RealmObject;

public class MovieID  extends RealmObject {
    private String id;

    public MovieID(String id) {
        this.id = id;
    }

    public MovieID() {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
