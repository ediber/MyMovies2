package com.mymovies2;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mymovies2.data.IMovieHeadline;
import com.mymovies2.data.MoviesList;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GetMoviesTask extends AsyncTask <String, String, String> {

    private IMoviesListListener listListener;

    public GetMoviesTask(IMoviesListListener listListener) {
        this.listListener = listListener;
    }

    @Override
    protected String doInBackground(String... strings) {
        String message = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("https://api.themoviedb.org/3/movie/top_rated?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1");

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                //System.out.print(current);
                message = message + current;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return message; // on return onPostExecute starts on main thread
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Gson gson = new GsonBuilder().create();
        MoviesList moviesList = gson.fromJson(s, MoviesList.class);

        listListener.onMoviesReady(moviesList);

      /*  Type listType = new TypeToken<ArrayList<RecipeJson>>(){}.getType();
        List<RecipeJson> recipes = new Gson().fromJson(s, listType);
        listener.onReady(recipes);*/

      /*  Type listType = new TypeToken<ArrayList<Recipe>>(){}.getType();
        List<Recipe> recipes = new Gson().fromJson(s, listType);
        listener.onReady(recipes);*/
    }

    public interface IMoviesListListener{
        void onMoviesReady(MoviesList movies);
    }
}
