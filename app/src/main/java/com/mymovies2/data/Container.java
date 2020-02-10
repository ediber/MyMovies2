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
    //private RealmResults<MovieID> selectedIds;
    private RealmResults<User> users;
    private RealmResults<Selected_User> selected_user;
    private User currentUser;



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

    //    selectedIds = realm.where(MovieID.class).findAll();
        users = realm.where(User.class).findAll();
        selected_user = realm.where(Selected_User.class).findAll();

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
            for (Selected_User selected_user : selected_user) {
                if(selected_user.getEmail().equals(currentUser.getEmail()) &&
                        selected_user.getMovieId().equals(headline.getId())){
                    headline.setIsSelected(true);
                }
            }
        }

        return headlines;
    }

    public void changeSelected(IMovieHeadline headline) {
        for (final IMovieHeadline myHeadline : headlines) {
            if (myHeadline.getId().equals(headline.getId())) { // find headline in the class which has the same id of the selected headline
                changeSpecificMovie(myHeadline);
            }
        }
        this.headlines = refreshSelected(headlines);
    }

    private void changeSpecificMovie(final IMovieHeadline myHeadline) {
        if (myHeadline.getIsSelected()) {  // selected, deleting
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Selected_User> result = realm.where(Selected_User.class).equalTo("movieId", myHeadline.getId())
                            .equalTo("email", currentUser.getEmail()).findAll();
                    result.deleteAllFromRealm();
                }
            });

        } else { // not selected
            realm.beginTransaction();
            Selected_User selected_user = new Selected_User(myHeadline.getId(), currentUser.getEmail());
            realm.copyToRealm(selected_user);
            realm.commitTransaction();
        }
    }


   /* private void changeSpecificMovie(final IMovieHeadline myHeadline) {
        if (myHeadline.getIsSelected()) {  // selected, deleting
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<MovieID> result = realm.where(MovieID.class).equalTo("id", myHeadline.getId()).findAll();
                    result.deleteAllFromRealm();
                }
            });

        } else { // not selected
            realm.beginTransaction();
            MovieID id = new MovieID(myHeadline.getId());
            realm.copyToRealm(id);
            realm.commitTransaction();
        }
    }*/

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

    public void addUser(User user) {
        realm.beginTransaction();
        realm.copyToRealm(user);
        users = realm.where(User.class).findAll();
        realm.commitTransaction();
    }

    public RealmResults<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
