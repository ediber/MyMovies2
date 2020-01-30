package com.mymovies2.data;

import android.content.Context;

import com.mymovies2.GetMoviesTask;

import java.util.List;

public class DAO {
    // static variable single_instance of type Singleton
    private static DAO single_instance = null;
    private final Context context;
    private Container container;
    private IMoviesListListener _headlinesListener;


    // private constructor restricted to this class itself
    private DAO(Context context) {
        container = new Container(context);
        this.context = context;
    }

    // static method to create instance of Singleton class
    public static DAO getInstance(Context context)
    {
        if (single_instance == null)
            single_instance = new DAO(context);

        return single_instance;
    }

    public void getMoviesList(IMoviesListListener headlinesListener){
        _headlinesListener = headlinesListener;

       /* List<IMovieHeadline> containerHeadlines = container.getMoviesHeadlines();
        if(containerHeadlines.size() > 0){
            _headlinesListener.onMoviesReady(containerHeadlines);
        }
        else {
            executeMoviesTask();
        }*/

        executeMoviesTask();
    }

    private void executeMoviesTask() {
        GetMoviesTask task = new GetMoviesTask(new GetMoviesTask.IMoviesListListener() {

            @Override
            public void onMoviesReady(MoviesList movies) {
                List<IMovieHeadline> headlines = movies.getHeadlineResults();
                container.setHeadlines(headlines);
                _headlinesListener.onMoviesReady(headlines);
            }
        });
        task.execute(); // start doingBackGround
    }

    public void changeSelected(IMovieHeadline headline) {
        container.changeSelected(headline);
    }

    public List<IMovieHeadline> getSelectedMovies() {
        return container.getSelectedMovies();
    }

    public Movie getMovieById(String id) {
        return container.getMovieById(id);
    }

    public interface IMoviesListListener{
        void onMoviesReady(List<IMovieHeadline> headlines);
    }
}
