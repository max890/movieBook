package com.powercode.test.max.moviebook.ui.activities.base;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.powercode.test.max.moviebook.app.di.qualifiers.ActivityContext;
import com.powercode.test.max.moviebook.app.di.scopes.PerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule {

    @Binds
    @ActivityContext
    @PerActivity
    abstract Context bindContext(final AppCompatActivity activity);

    @Provides
    @PerActivity
    static FragmentManager provideFragmentManager(final AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}
