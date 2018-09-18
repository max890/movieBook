package com.powercode.test.max.moviebook.app.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.powercode.test.max.moviebook.app.di.qualifiers.AppContext;
import com.powercode.test.max.moviebook.model.db.AppDatabase;
import com.powercode.test.max.moviebook.model.db.dao.HistoryMovieDao;
import com.powercode.test.max.moviebook.model.db.dao.ShortMovieDao;

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
    static ShortMovieDao provideDetailsMovieDao(final AppDatabase database) {
        return database.shortMovieDao();
    }

    @Provides
    @Singleton
    static HistoryMovieDao provideHistoryMovieDao(final AppDatabase database) {
        return database.historyMovieDao();
    }
}
