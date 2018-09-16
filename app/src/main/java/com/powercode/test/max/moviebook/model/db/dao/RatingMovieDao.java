package com.powercode.test.max.moviebook.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.powercode.test.max.moviebook.model.entity.RatingMovieModel;

import java.util.List;

@Dao
public interface RatingMovieDao {

    @Query("SELECT * FROM ratingMovie")
    List<RatingMovieModel> getRatings();

    @Query("SELECT * FROM ratingMovie WHERE MovieId = :movieId")
    List<RatingMovieModel> getRatingForMovie(String movieId);

    @Insert
    void insert(RatingMovieModel ratingMovie);

    @Query("DELETE FROM ratingMovie WHERE MovieId=:movieId")
    void deleteForId(long movieId);
}
