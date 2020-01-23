package com.mymovies2.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymovies2.R;
import com.mymovies2.data.DAO;
import com.mymovies2.data.Movie;
import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {

    private String id;
    private ImageView imageView;
    private TextView name;
    private TextView releaseDate;
    private TextView popularity;
    private TextView originalLanguage;
    private TextView overview;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public DetailsFragment(String id) {
        this.id = id;
    }


    public static DetailsFragment newInstance(String id) {
        DetailsFragment fragment = new DetailsFragment(id);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        imageView = view.findViewById(R.id.details_imageView);
        name = (TextView) view.findViewById(R.id.details_name);
        releaseDate = (TextView) view.findViewById(R.id.details_release_date);
        popularity = (TextView) view.findViewById(R.id.details_popularity);
        originalLanguage = (TextView) view.findViewById(R.id.details_original_language);
        overview = (TextView) view.findViewById(R.id.details_overview);

        Movie movie = DAO.getInstance().getMovieById(id);

        name.setText(movie.getTitle());
        releaseDate.setText(movie.getRelease_date());
        popularity.setText(movie.getPopularity() + "");
        originalLanguage.setText(movie.getOriginal_language());
        overview.setText(movie.getOverview());

        String url = "https://image.tmdb.org/t/p/original///" + movie.getPoster_path();
        Picasso.get().load(url).into(imageView);


        return view;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
