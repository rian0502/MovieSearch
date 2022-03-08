package com.belajar.movielist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belajar.movielist.Models.Movie;
import com.belajar.movielist.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter <AdapterMovie.MyViewHolder> {

    private Context context;
    private final List< Movie > movies;

    public AdapterMovie(Context context, List< Movie > movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public AdapterMovie.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovie.MyViewHolder holder, int position) {
        holder.title_film.setText(movies.get(position).getTitle());
        holder.type_film.setText(movies.get(position).getType());
        holder.id_film.setText(movies.get(position).getImdbID());
        holder.tahun_rilis.setText(movies.get(position).getYear());
        Glide.with(context).load(movies.get(position).getPoster()).into(holder.poster_film);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView poster_film;
        TextView title_film;
        TextView type_film;
        TextView tahun_rilis;
        TextView id_film;
        public MyViewHolder(View itemView) {
            super(itemView);
            poster_film = itemView.findViewById(R.id.poster_film);
            title_film = itemView.findViewById(R.id.title_film);
            type_film = itemView.findViewById(R.id.type_film);
            tahun_rilis = itemView.findViewById(R.id.tahun_rilis);
            id_film = itemView.findViewById(R.id.id_film);
        }
    }
}
