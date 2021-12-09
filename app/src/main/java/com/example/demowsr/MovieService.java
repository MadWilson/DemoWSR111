package com.example.demowsr;



import com.example.demowsr.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("/movies?filter=new")
    Call<List<Movies>> getMovies();
}
