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

public class SelectedAdapter extends RecyclerView.Adapter {

    private AdapterListener listener;
    private Context context;
    private List<IMovieHeadline> headlines;

    public SelectedAdapter(Context context, List<IMovieHeadline> headlines, AdapterListener listener) {
        this.headlines = headlines;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.selected_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myHolder = ((MyViewHolder)holder);
        final TextView title = myHolder.title;
        ImageView image = myHolder.image;
        View parent = myHolder.parent;
        final IMovieHeadline movie = headlines.get(position);
        title.setText(movie.getTitle());

        String url = "https://image.tmdb.org/t/p/original///" + movie.getPoster_path();
        Picasso.get().load(url).into(image);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(movie.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
//        return lst.size();
        return headlines.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        public TextView title;
        public View parent;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.selected_row_title);
            image = view.findViewById(R.id.selected_row_poster);
            parent = view.findViewById(R.id.selected_row_parent);
        }
    }

    public interface AdapterListener{
        void onItemClicked(String id);
    }
}
