package com.belajar.movielist.API;

import com.belajar.movielist.Models.Movie;
import com.belajar.movielist.Models.Status;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIsend {

    @GET("/")
    Call< Status > getMovie(@Query("apikey") String key, @Query("s") String movie);

}
