package com.mymovies2.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

class Container {

    private Realm realm;
    private RealmResults<Movie> headlines;

    public Container(Context context) {
        //  this.headlines = new ArrayList<>();

        // Initialize Realm (just once per application)
        Realm.init(context);

// Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();

        headlines = realm.where(Movie.class).findAll();


    }

    public void setHeadlines(List<IMovieHeadline> headlines) {
        for (IMovieHeadline headline : headlines) {
            realm.beginTransaction();
            realm.copyToRealm((Movie) headline);
            realm.commitTransaction();
        }
        this.headlines = realm.where(Movie.class).findAll();
    }

    public void changeSelected(IMovieHeadline headline) {
        for (IMovieHeadline myHeadline : headlines) {
            if (myHeadline.getId().equals(headline.getId())) {
                realm.beginTransaction();
                if (myHeadline.getIsSelected()) {
                    myHeadline.setIsSelected(false);
                } else { // not selected
                    myHeadline.setIsSelected(true);
                }
                realm.commitTransaction();
            }
        }
    }

    public List<IMovieHeadline> getSelectedMovies() {
        List<IMovieHeadline> selected = new ArrayList<>();

        for (IMovieHeadline headline : headlines)
            if (headline.getIsSelected()) {
                selected.add(headline);
            }

        return selected;
    }

    public List<IMovieHeadline> getMoviesHeadlines() {
        List<IMovieHeadline> simpleHeadlines = new ArrayList<>();

        for (IMovieHeadline headline : headlines) {
            simpleHeadlines.add(headline);
        }

        return simpleHeadlines;
    }

    public Movie getMovieById(String id) {
        for (IMovieHeadline headline : headlines) {
            if (headline.getId().equals(id)) {
                return (Movie) headline;
            }
        }
        return null;
    }
}
