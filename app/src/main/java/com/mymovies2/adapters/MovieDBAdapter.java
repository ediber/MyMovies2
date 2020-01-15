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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDBAdapter extends RecyclerView.Adapter {

    private  AdapterListener listener;
    private Context context;
    private List<IMovieHeadline> headlines;
    private List<Boolean> selectedRows;



    public MovieDBAdapter(List<IMovieHeadline> headlines, List<IMovieHeadline> selected, Context context, AdapterListener listener) {
        this.headlines = headlines;
        this.context = context;
        this.listener = listener;
     /*   selectedRows = new ArrayList<>();
        for (int i = 0; i < headlines.size(); i++) {
            selectedRows.set(i, false);
        }

        for (int i = 0; i < selected.size(); i++) {
            this.selectedRows.set(i, selected.get(i).getIsSelected());
        }*/
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.movie_db_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myHolder = (MyViewHolder) holder;
        IMovieHeadline headline = headlines.get(position);
        myHolder.title.setText(headline.getTitle());
        ImageView poster = myHolder.poster;
        String url = "https://image.tmdb.org/t/p/original///" + headline.getPoster_path();

        Picasso.get().load(url).into(poster);

        if(selectedRows.get(position)) {
            myHolder.parent.setBackgroundColor(0xAAFFFF00);
        } else {
            myHolder.parent.setBackgroundColor(0x00000000);
        }

        myHolder.parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                updateUI(position, myHolder);

                listener.onLongClicked(headlines.get(position));

                return false;
            }
        });

    }

    private void updateUI(int position, MyViewHolder myHolder) {
        if(! selectedRows.get(position)){ // if selectedRows is false
            selectedRows.set(position, true);
            myHolder.parent.setBackgroundColor(0xAAFFFF00);
        } else {
            selectedRows.set(position, false);
            myHolder.parent.setBackgroundColor(0x00000000);
        }
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
