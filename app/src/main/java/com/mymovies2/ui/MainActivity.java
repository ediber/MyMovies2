package com.mymovies2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.mymovies2.data.DAO;
import com.mymovies2.R;
import com.mymovies2.data.IMovieHeadline;

import java.util.List;



/*api key

        a921476d861fb36a167704c00cb03bfb

        https://api.themoviedb.org/3/movie/top_rated?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1

        https://api.themoviedb.org/3/movie/upcoming?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1

        https://image.tmdb.org/t/p/original///2CAL2433ZeIihfX1Hb2139CX0pW.jpg*/

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame;
    private SelectedMoviesFragment selectedMoviesFragment;
    private FragmentManager fragmentManager;
    private DAO.IMoviesListListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.main_frame);

        selectedMoviesFragment = SelectedMoviesFragment.newInstance(new SelectedMoviesFragment.fragmentInteractionListener() {
            @Override
            public void onAddClicked() {
                // move to MovieDBFragment
                MovieDBFragment fragment = MovieDBFragment.newInstance();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_frame, fragment).commit();
            }
        });

        fragmentManager = getSupportFragmentManager();
        // move to selectedMoviesFragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_frame, selectedMoviesFragment).commit();

        listener = new DAO.IMoviesListListener() {
            @Override
            public void onMoviesReady(List<IMovieHeadline> headlines) {

            }
        };

        findViewById(R.id.dao_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO.getInstance().getMoviesList(listener);
            }
        });


    }
}
