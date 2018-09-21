package com.powercode.test.max.moviebook.app.di.modules;

import com.powercode.test.max.moviebook.app.di.scopes.PerActivity;
import com.powercode.test.max.moviebook.ui.activities.details.DetailsActivity;
import com.powercode.test.max.moviebook.ui.activities.details.DetailsActivityModule;
import com.powercode.test.max.moviebook.ui.activities.history.HistoryActivity;
import com.powercode.test.max.moviebook.ui.activities.history.HistoryActivityModule;
import com.powercode.test.max.moviebook.ui.activities.search.SearchActivity;
import com.powercode.test.max.moviebook.ui.activities.search.SearchActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ComponentsModule {

    //Activities
    @ContributesAndroidInjector(modules = SearchActivityModule.class)
    @PerActivity
    abstract SearchActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = HistoryActivityModule.class)
    @PerActivity
    abstract HistoryActivity contributeHistoryActivity();

    @ContributesAndroidInjector(modules = DetailsActivityModule.class)
    @PerActivity
    abstract DetailsActivity contributeDetailsActivity();
}
