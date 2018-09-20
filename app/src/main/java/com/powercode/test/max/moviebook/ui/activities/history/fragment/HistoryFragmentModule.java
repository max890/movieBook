package com.powercode.test.max.moviebook.ui.activities.history.fragment;

import android.arch.lifecycle.ViewModel;

import com.powercode.test.max.moviebook.app.di.scopes.PerFragment;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.ViewModelKey;
import com.powercode.test.max.moviebook.ui.activities.history.fragment.presenter.HistoryFragmentPresenter;
import com.powercode.test.max.moviebook.ui.activities.history.fragment.presenter.HistoryFragmentPresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class HistoryFragmentModule {

    @Binds
    @PerFragment
    abstract HistoryFragmentPresenter bindPresenter(final HistoryFragmentPresenterImpl impl);

    @ViewModelKey(HistoryFragmentPresenter.class)
    @Binds
    @IntoMap
    abstract ViewModel bindHistoryFragmentPresenter(HistoryFragmentPresenter presenter);
}
