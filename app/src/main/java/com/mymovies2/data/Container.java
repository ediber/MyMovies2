package com.mymovies2.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

class Container {

    private  Realm realm;
    private List<IMovieHeadline> headlines;

    public Container(Context context) {
      //  this.headlines = new ArrayList<>();

        // Initialize Realm (just once per application)
        Realm.init(context);

// Get a Realm instance for this thread
        realm = Realm.getDefaultInstance();

        this.headlines = (List<IMovieHeadline>) realm.where(Movie.class);
        if(headlines == null){
            headlines = new RealmList();
        }
    }

    public void setHeadlines(List<IMovieHeadline> headlines) {
        this.headlines = headlines;
    }

    public void changeSelected(IMovieHeadline headline) {
        for (IMovieHeadline myHeadline : headlines) {
            if(myHeadline.getId().equals(headline.getId())){
                if(myHeadline.getIsSelected()){
                    myHeadline.setIsSelected(false);
                } else { // not selected
                    myHeadline.setIsSelected(true);
                }
            }
        }
    }

    public List<IMovieHeadline> getSelectedMovies() {
        List<IMovieHeadline> selected = new ArrayList<>();

        for (IMovieHeadline headline : headlines)
        if(headline.getIsSelected()){
            selected.add(headline);
        }
        return selected;
    }

    public List<IMovieHeadline> getMoviesHeadlines() {
        return headlines;
    }

    public Movie getMovieById(String id) {
        for (IMovieHeadline headline : headlines) {
            if(headline.getId().equals(id)){
                return  (Movie)headline;
            }
        }
        return null;
    }
}
