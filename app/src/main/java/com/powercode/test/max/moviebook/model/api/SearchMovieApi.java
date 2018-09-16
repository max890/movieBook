package com.powercode.test.max.moviebook.model.api;

import com.powercode.test.max.moviebook.model.entity.DetailsMovieModel;
import com.powercode.test.max.moviebook.model.entity.SearchMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchMovieApi {

    @GET("/")
    Call<SearchMovieModel> searchMovies(@Query("s") String search, @Query("page") int page);

    @GET("/")
    Call<DetailsMovieModel> getDetailsMovie(@Query("i") String movieId);
}
