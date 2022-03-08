package com.belajar.movielist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.belajar.movielist.API.APICLIENT;
import com.belajar.movielist.Models.Movie;
import com.belajar.movielist.Models.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        SearchView searchView = findViewById(R.id.searchView);
        textView = findViewById(R.id.testing);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        reqMovie();
        return true;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void reqMovie(){
        Call<Status> movieCall = APICLIENT.getService().getMovie("66b856f6","Narcos");
        movieCall.enqueue(new Callback< Status >() {
            @Override
            public void onResponse(Call< Status > call, Response< Status > response) {
                if(response.isSuccessful()){
                    String txt = "";
                    assert response.body() != null;
                    List<Movie> movies = response.body().getSearch();
                    for(Movie movie : movies){
                        txt += movie.getTitle() + "\n";
                    }
                    textView.setText(txt);
                }
            }
            @Override
            public void onFailure(Call< Status > call, Throwable t) {

            }
        });
    }

}