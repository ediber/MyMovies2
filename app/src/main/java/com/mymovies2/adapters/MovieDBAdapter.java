package com.mymovies2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymovies2.R;
import com.mymovies2.data.IMovieHeadline;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDBAdapter extends RecyclerView.Adapter {

    private  AdapterListener listener;
    private Context context;
    private List<IMovieHeadline> headlines;

    public MovieDBAdapter(List<IMovieHeadline> headlines, Context context, AdapterListener listener) {
        this.headlines = headlines;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.movie_db_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        IMovieHeadline headline = headlines.get(position);
        myHolder.title.setText(headline.getTitle());
        ImageView poster = myHolder.poster;
        String url = "https://image.tmdb.org/t/p/original///" + headline.getPoster_path();

        Picasso.get().load(url).into(poster);

        myHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        public View parent;
        public ImageView poster;
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.db_row_title);
            poster = view.findViewById(R.id.db_row_poster);
            parent = view.findViewById(R.id.db_row_parent);
        }
    }

    public interface AdapterListener{
        void onLongClicked(IMovieHeadline headline);
    }
}
