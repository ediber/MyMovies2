package com.mymovies2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mymovies2.AlertDialogHelper;
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
    private TextView user;
    private View logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.main_frame);
        user = findViewById(R.id.main_current_user);
        logout = findViewById(R.id.main_logout);

        selectedMoviesFragment = SelectedMoviesFragment.newInstance(new SelectedMoviesFragment.fragmentInteractionListener() {
            @Override
            public void onAddClicked() {
                // move to MovieDBFragment
                MovieDBFragment dbfragment = MovieDBFragment.newInstance(new MovieDBFragment.FragmentListener() {
                    @Override
                    public void onSaveClicked() {
                        // move to selectedMoviesFragment
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
   //                     transaction.replace(R.id.main_frame, selectedMoviesFragment).addToBackStack(null).commit();
                        transaction.replace(R.id.main_frame, selectedMoviesFragment).commit();
                    }
                });
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_frame, dbfragment).addToBackStack(null).commit();
        //        transaction.replace(R.id.main_frame, fragment).commit();
            }

            @Override
            public void onItemClicked(String id) {
                DetailsFragment fragment = DetailsFragment.newInstance(id);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_frame, fragment).addToBackStack(null).commit();
            }
        });

        fragmentManager = getSupportFragmentManager();
        // move to selectedMoviesFragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame, selectedMoviesFragment).addToBackStack("selected_transaction").commit();

        listener = new DAO.IMoviesListListener() {
            @Override
            public void onMoviesReady(List<IMovieHeadline> headlines) {

            }
        };

        findViewById(R.id.dao_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO.getInstance(getApplicationContext()).getMoviesList(listener);
            }
        });

        String firstName = DAO.getInstance(getApplicationContext()).getCurrentUserName();
        user.setText("hello " + firstName);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            new AlertDialogHelper(this, new AlertDialogHelper.DialogListener() {
                @Override
                public void onYesClicked() {
                    //MainActivity.super.onBackPressed();
                 //   finish();

                    Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                    homeIntent.addCategory( Intent.CATEGORY_HOME );
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeIntent);
                }

                @Override
                public void onNoClicked() {

                }
            }).showDialog();
        }
    }
}
