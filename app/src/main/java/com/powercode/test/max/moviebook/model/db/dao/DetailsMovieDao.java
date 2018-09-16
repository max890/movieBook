package com.powercode.test.max.moviebook.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.powercode.test.max.moviebook.model.entity.DetailsMovieModel;

import java.util.List;

@Dao
public interface DetailsMovieDao {

    @Query("SELECT * FROM movies")
    List<DetailsMovieModel> getMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    DetailsMovieModel getMovie(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DetailsMovieModel movie);

    @Delete
    void delete(DetailsMovieModel movie);

}
