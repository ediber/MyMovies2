package com.mymovies2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovies2.R;
import com.mymovies2.adapters.SelectedAdapter;


public class SelectedMoviesFragment extends Fragment {


    public SelectedMoviesFragment() {
        // Required empty public constructor
    }


    public static SelectedMoviesFragment newInstance() {
        SelectedMoviesFragment fragment = new SelectedMoviesFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_selected_movies, container, false);


        RecyclerView recycler = view.findViewById(R.id.recycler);
        SelectedAdapter adapter = new SelectedAdapter(getContext());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


}
