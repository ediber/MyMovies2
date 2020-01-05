package com.mymovies2.data;

import com.mymovies2.GetMoviesTask;

import java.util.List;

public class DAO {
    // static variable single_instance of type Singleton
    private static DAO single_instance = null;
    private IMoviesListListener headlinesList;


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

    public void getMoviesList(IMoviesListListener headlinesList){
        this.headlinesList = headlinesList;

        GetMoviesTask task = new GetMoviesTask();
        task.execute(); // start doingBackGround
    }

    public interface IMoviesListListener{
        void onMoviesReady(List<IMovieHeadline> headlines);
    }
}
