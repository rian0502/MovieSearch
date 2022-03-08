package com.belajar.movielist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.belajar.movielist.API.APICLIENT;
import com.belajar.movielist.Adapters.AdapterMovie;
import com.belajar.movielist.Models.Movie;
import com.belajar.movielist.Models.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        SearchView searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.container_movie);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        reqMovie(query.toLowerCase(Locale.ROOT));
        return true;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void reqMovie(String txt){
        Call<Status> movieCall = APICLIENT.getService().getMovie("66b856f6",txt);
        movieCall.enqueue(new Callback< Status >() {
            @Override
            public void onResponse(@NonNull Call< Status > call, @NonNull Response< Status > response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    List<Movie> mv = response.body().getSearch();
                    Toast.makeText(MainActivity.this, ""+mv.size(), Toast.LENGTH_SHORT).show();
                    movies = mv;
                    putDataIntoRecyCleView(movies);
                }
            }
            @Override
            public void onFailure(@NonNull Call< Status > call, @NonNull Throwable t) {

            }
        });
    }

    private void putDataIntoRecyCleView(List< Movie> movies) {
        AdapterMovie adapterMovie = new AdapterMovie(this, movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterMovie);
    }

}