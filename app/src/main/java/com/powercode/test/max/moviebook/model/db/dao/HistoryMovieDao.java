package com.powercode.test.max.moviebook.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;

import java.util.List;

@Dao
public interface HistoryMovieDao {

    @Query("SELECT * FROM history ORDER BY position ASC")
    List<SearchHistoryModel> getHistories();

    @Query("SELECT MAX(position) FROM history")
    int getMaxPosition();

    @Query("SELECT COUNT(id) FROM history")
    int getSize();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(SearchHistoryModel historyModel);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<SearchHistoryModel> searchHistoryModels);

    @Query("DELETE FROM history WHERE position == :position")
    int deleteByPosition(int position);

    @Delete
    int delete(SearchHistoryModel historyModel);
}
