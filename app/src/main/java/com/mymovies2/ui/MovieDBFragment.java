package com.mymovies2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovies2.R;
import com.mymovies2.adapters.MovieDBAdapter;
import com.mymovies2.data.DAO;
import com.mymovies2.data.IMovieHeadline;

import java.util.List;


public class MovieDBFragment extends Fragment {

    private FragmentListener listener;
    private RecyclerView recycler;
    private View save;

    public MovieDBFragment() {
        // Required empty public constructor
    }

    public MovieDBFragment(FragmentListener listener) {
        this.listener = listener;
    }


    public static MovieDBFragment newInstance(FragmentListener listener) {
        MovieDBFragment fragment = new MovieDBFragment(listener);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_db, container, false);

        recycler = view.findViewById(R.id.movie_db_recycler);
        save = view.findViewById(R.id.movie_db_save);


        DAO.getInstance(getContext()).getMoviesList(new DAO.IMoviesListListener() {
            @Override
            public void onMoviesReady(List<IMovieHeadline> headlines) {
                MovieDBAdapter adapter = new MovieDBAdapter(headlines, getContext(), new MovieDBAdapter.AdapterListener() {
                    @Override
                    public void onLongClicked(IMovieHeadline headline) {
                        DAO.getInstance(getContext()).changeSelected(headline);
                    }
                });
                recycler.setAdapter(adapter);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSaveClicked();
            }
        });

        return view;
    }


    public interface FragmentListener{
        void onSaveClicked();
    }
}
