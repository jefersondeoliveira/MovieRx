package com.example.jeferson.movierx.data.service;

import com.example.jeferson.movierx.data.model.Movie;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jeferson on 12/09/16.
 */
public interface MovieService {

    @GET("list_movies.json")
    Observable<List<Movie>> getAllMovies(@Query("page") int pageNumber);

    @GET("list_movies.json")
    Observable<List<Movie>> getAllMoviesByGenre(@Query("genre") String genre, @Query("page") int pageNumber);

}
