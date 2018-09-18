package com.powercode.test.max.moviebook.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.powercode.test.max.moviebook.model.entity.ShortMovieModel;

import java.util.List;

@Dao
public interface ShortMovieDao {

    @Query("SELECT * FROM movies")
    List<ShortMovieModel> getMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    ShortMovieModel getMovie(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ShortMovieModel movie);

    @Delete
    void delete(ShortMovieModel movie);

}
