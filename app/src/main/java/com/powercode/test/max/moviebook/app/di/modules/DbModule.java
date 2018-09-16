package com.powercode.test.max.moviebook.app.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.powercode.test.max.moviebook.app.di.qualifiers.AppContext;
import com.powercode.test.max.moviebook.model.db.AppDatabase;
import com.powercode.test.max.moviebook.model.db.dao.DetailsMovieDao;
import com.powercode.test.max.moviebook.model.db.dao.HistoryMovieDao;
import com.powercode.test.max.moviebook.model.db.dao.RatingMovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    static AppDatabase provideAppDataBase(@AppContext final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "movies_db")
                .fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    @Singleton
    static DetailsMovieDao provideDetailsMovieDao(final AppDatabase database) {
        return database.detailsMovieDao();
    }

    @Provides
    @Singleton
    static RatingMovieDao provideRatingMovieModel(final AppDatabase database) {
        return database.ratingMovieDao();
    }

    @Provides
    @Singleton
    static HistoryMovieDao provideHistoryMovieDao(final AppDatabase database) {
        return database.historyMovieDao();
    }
}
