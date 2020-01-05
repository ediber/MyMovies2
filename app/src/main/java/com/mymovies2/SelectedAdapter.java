package com.mymovies2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectedAdapter extends RecyclerView.Adapter {

    private Context context;
    List<String> lst;           //

    SelectedAdapter(Context context) {
        lst = Arrays.asList(new String[]{"s", "dd", "gggg"});
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView text = ((MyViewHolder)holder).text;
        String item = lst.get(position);
        text.setText(item);

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public MyViewHolder(View view) {
            super(view);
            text = view.findViewById(R.id.row_text0);
        }
    }
}
