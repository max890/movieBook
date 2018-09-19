package com.powercode.test.max.moviebook.model.api;

import com.powercode.test.max.moviebook.model.entity.DetailsMovieModel;
import com.powercode.test.max.moviebook.model.entity.SearchMovieModel;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchMovieApi {

    @GET("/")
    Single<SearchMovieModel> searchMovies(@Query("s") String search, @Query("page") int page);

    @GET("/")
    Observable<DetailsMovieModel> getDetailsMovie(@Query("i") String movieId);
}
