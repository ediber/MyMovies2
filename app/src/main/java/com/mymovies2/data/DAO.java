package com.mymovies2.data;

import com.mymovies2.GetMoviesTask;

import java.util.List;

public class DAO {
    // static variable single_instance of type Singleton
    private static DAO single_instance = null;
    private IMoviesListListener _headlinesListener;


    // private constructor restricted to this class itself
    private DAO() {

    }

    // static method to create instance of Singleton class
    public static DAO getInstance()
    {
        if (single_instance == null)
            single_instance = new DAO();

        return single_instance;
    }

    public void getMoviesList(IMoviesListListener headlinesListener){
        _headlinesListener = headlinesListener;

        GetMoviesTask task = new GetMoviesTask(new GetMoviesTask.IMoviesListListener() {

            @Override
            public void onMoviesReady(MoviesList movies) {
                List<IMovieHeadline> headlines = movies.getHeadlineResults();
                _headlinesListener.onMoviesReady(headlines);
            }
        });
        task.execute(); // start doingBackGround
    }

    public interface IMoviesListListener{
        void onMoviesReady(List<IMovieHeadline> headlines);
    }
}
