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
import com.mymovies2.data.DAO;


public class SelectedMoviesFragment extends Fragment {


    private fragmentInteractionListener listener;

    public SelectedMoviesFragment() {
        // Required empty public constructor
    }

    public SelectedMoviesFragment(fragmentInteractionListener listener) {
        this.listener = listener;
    }


    public static SelectedMoviesFragment newInstance(fragmentInteractionListener listener) {
        SelectedMoviesFragment fragment = new SelectedMoviesFragment(listener);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_selected_movies, container, false);

         view.findViewById(R.id.selected_movies_add).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 listener.onAddClicked();
             }
         });

        RecyclerView recycler = view.findViewById(R.id.selected_movies_recycler);
        SelectedAdapter adapter = new SelectedAdapter(getContext(), DAO.getInstance().getSelectedMovies(), new SelectedAdapter.AdapterListener() {
            @Override
            public void onItemClicked(String id) {
                listener.onItemClicked(id);
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    public interface fragmentInteractionListener{
        void onAddClicked();
        void onItemClicked(String id);
    }





}
