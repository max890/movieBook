package com.powercode.test.max.moviebook.model.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.powercode.test.max.moviebook.model.db.dao.DetailsMovieDao;
import com.powercode.test.max.moviebook.model.db.dao.HistoryMovieDao;
import com.powercode.test.max.moviebook.model.db.dao.RatingMovieDao;
import com.powercode.test.max.moviebook.model.entity.DetailsMovieModel;
import com.powercode.test.max.moviebook.model.entity.RatingMovieModel;
import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;

@Database(entities = {DetailsMovieModel.class, RatingMovieModel.class, SearchHistoryModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DetailsMovieDao detailsMovieDao();

    public abstract RatingMovieDao ratingMovieDao();

    public abstract HistoryMovieDao historyMovieDao();

}
