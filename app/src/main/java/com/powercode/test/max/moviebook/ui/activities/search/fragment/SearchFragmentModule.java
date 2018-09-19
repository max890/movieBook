package com.powercode.test.max.moviebook.ui.activities.search.fragment;

import android.arch.lifecycle.ViewModel;

import com.powercode.test.max.moviebook.app.di.scopes.PerFragment;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.ViewModelKey;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.presenter.SearchFragmentPresenter;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.presenter.SearchFragmentPresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SearchFragmentModule {

    @Binds
    @PerFragment
    abstract SearchFragmentPresenter bindPresenter(final SearchFragmentPresenterImpl impl);

    @ViewModelKey(SearchFragmentPresenter.class)
    @Binds
    @IntoMap
    abstract ViewModel bindSearchFragmentPresenter(SearchFragmentPresenter presenter);
}

