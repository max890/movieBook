package com.powercode.test.max.moviebook.app.di.modules;

import com.powercode.test.max.moviebook.app.di.scopes.PerActivity;
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
}
