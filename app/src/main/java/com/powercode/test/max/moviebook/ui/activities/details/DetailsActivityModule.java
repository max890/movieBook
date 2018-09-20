package com.powercode.test.max.moviebook.ui.activities.details;

import android.support.v7.app.AppCompatActivity;

import com.powercode.test.max.moviebook.app.di.scopes.PerActivity;
import com.powercode.test.max.moviebook.app.di.scopes.PerFragment;
import com.powercode.test.max.moviebook.ui.activities.base.BaseActivityModule;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.DetailsFragment;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.DetailsFragmentModule;
import com.powercode.test.max.moviebook.ui.activities.details.view.DetailsActivityView;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = BaseActivityModule.class)
public abstract class DetailsActivityModule {

    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(final DetailsActivity activity);

    @Binds @PerActivity
    abstract DetailsActivityView bindDetailsActivityView(final DetailsActivity activity);

    @ContributesAndroidInjector(modules = DetailsFragmentModule.class)
    @PerFragment
    abstract DetailsFragment contributeDetailsFragment();
}
