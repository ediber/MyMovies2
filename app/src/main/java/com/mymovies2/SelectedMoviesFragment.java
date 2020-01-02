package com.mymovies2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



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

         return view;
    }


}
