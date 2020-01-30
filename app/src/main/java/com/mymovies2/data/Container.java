package com.mymovies2.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

class Container {

    private Realm realm;
    private List<IMovieHeadline> headlines;
    private RealmResults<MovieID> selectedIds;

    public Container(Context context) {
          this.headlines = new ArrayList<>();

        // Initialize Realm (just once per application)
        Realm.init(context);

// Get a Realm instance for this thread
     //   realm = Realm.getDefaultInstance();


        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(config);

      //  headlines = realm.where(Movie.class).findAll();
        selectedIds = realm.where(MovieID.class).findAll();

    }

    public void setHeadlines(List<IMovieHeadline> headlines) {
       /* for (IMovieHeadline headline : headlines) {
            realm.beginTransaction();
            realm.copyToRealm((Movie) headline);
            realm.commitTransaction();
        }
        this.headlines = realm.where(Movie.class).findAll();*/

        this.headlines = refreshSelected(headlines);
    }

    private List<IMovieHeadline> refreshSelected(List<IMovieHeadline> headlines) {

        for (IMovieHeadline headline : headlines){
            headline.setIsSelected(false);
        }

        for (IMovieHeadline headline : headlines){
            for (MovieID id : selectedIds) {
                if(id.getId().equals(headline.getId())){
                    headline.setIsSelected(true);
                }
            }
        }

        return headlines;
    }

    public void changeSelected(IMovieHeadline headline) {
        for (final IMovieHeadline myHeadline : headlines) {
            if (myHeadline.getId().equals(headline.getId())) {
                if (myHeadline.getIsSelected()) {  // selected, deleting
                   // myHeadline.setIsSelected(false);
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<MovieID> result = realm.where(MovieID.class).equalTo("id", myHeadline.getId()).findAll();
                            result.deleteAllFromRealm();
                        }
                    });

                } else { // not selected
                  //  myHeadline.setIsSelected(true);
                    realm.beginTransaction();
                    MovieID id = new MovieID(myHeadline.getId());
                    realm.copyToRealm(id);
                    realm.commitTransaction();
                }
            }
        }
        this.headlines = refreshSelected(headlines);
    }

    public List<IMovieHeadline> getSelectedMovies() {
        this.headlines = refreshSelected(headlines);
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
