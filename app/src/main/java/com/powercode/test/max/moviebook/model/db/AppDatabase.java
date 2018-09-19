package com.powercode.test.max.moviebook.model.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.powercode.test.max.moviebook.model.db.dao.HistoryMovieDao;
import com.powercode.test.max.moviebook.model.db.dao.ShortMovieDao;
import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;
import com.powercode.test.max.moviebook.model.entity.ShortMovieModel;

@Database(entities = {ShortMovieModel.class, SearchHistoryModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ShortMovieDao shortMovieDao();

    public abstract HistoryMovieDao historyMovieDao();

}
