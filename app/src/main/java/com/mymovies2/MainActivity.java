package com.mymovies2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.FrameLayout;



/*api key

        a921476d861fb36a167704c00cb03bfb

        https://api.themoviedb.org/3/movie/top_rated?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1

        https://api.themoviedb.org/3/movie/upcoming?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1*/

public class MainActivity extends AppCompatActivity {

    private FrameLayout frame;
    private SelectedMoviesFragment selectedMoviesFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.main_frame);

        selectedMoviesFragment = SelectedMoviesFragment.newInstance();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_frame, selectedMoviesFragment).commit();





    }
}
