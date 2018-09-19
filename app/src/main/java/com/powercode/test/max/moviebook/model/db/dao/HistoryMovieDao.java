package com.powercode.test.max.moviebook.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;

import java.util.List;

@Dao
public interface HistoryMovieDao {

    @Query("SELECT * FROM history ORDER BY position ASC")
    List<SearchHistoryModel> getHistories();

    @Query("SELECT MAX(position) FROM history")
    int getMaxPosition();

    @Insert
    long insert(SearchHistoryModel historyModel);

    @Delete
    void delete(SearchHistoryModel historyModel);
}
