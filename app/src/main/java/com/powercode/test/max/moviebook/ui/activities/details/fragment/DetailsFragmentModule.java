package com.powercode.test.max.moviebook.ui.activities.details.fragment;

import android.arch.lifecycle.ViewModel;

import com.powercode.test.max.moviebook.app.di.scopes.PerFragment;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.ViewModelKey;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.presenter.DetailsFragmentPresenter;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.presenter.DetailsFragmentPresenterImpl;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailsFragmentModule {

    @Binds
    @PerFragment
    abstract DetailsFragmentPresenter bindPresenter(final DetailsFragmentPresenterImpl impl);

    @ViewModelKey(DetailsFragmentPresenter.class)
    @Binds
    @IntoMap
    abstract ViewModel bindDetailsFragmentPresenter(DetailsFragmentPresenter presenter);
}
