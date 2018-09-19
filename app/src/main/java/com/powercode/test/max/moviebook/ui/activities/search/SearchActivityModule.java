package com.powercode.test.max.moviebook.ui.activities.search;

import android.support.v7.app.AppCompatActivity;

import com.powercode.test.max.moviebook.app.di.scopes.PerActivity;
import com.powercode.test.max.moviebook.app.di.scopes.PerFragment;
import com.powercode.test.max.moviebook.ui.activities.base.BaseActivityModule;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.SearchFragment;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.SearchFragmentModule;
import com.powercode.test.max.moviebook.ui.activities.search.view.SearchActivityView;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = BaseActivityModule.class)
public abstract class SearchActivityModule {

    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(final SearchActivity activity);

    @Binds @PerActivity
    abstract SearchActivityView bindSearchActivityView(final SearchActivity activity);

    @ContributesAndroidInjector(modules = SearchFragmentModule.class)
    @PerFragment
    abstract SearchFragment contributeSearchFragment();

}
