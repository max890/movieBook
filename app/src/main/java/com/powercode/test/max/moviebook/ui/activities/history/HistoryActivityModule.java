package com.powercode.test.max.moviebook.ui.activities.history;

import android.support.v7.app.AppCompatActivity;

import com.powercode.test.max.moviebook.app.di.scopes.PerActivity;
import com.powercode.test.max.moviebook.app.di.scopes.PerFragment;
import com.powercode.test.max.moviebook.ui.activities.base.BaseActivityModule;
import com.powercode.test.max.moviebook.ui.activities.history.fragment.HistoryFragment;
import com.powercode.test.max.moviebook.ui.activities.history.fragment.HistoryFragmentModule;
import com.powercode.test.max.moviebook.ui.activities.history.view.HistoryActivityView;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = BaseActivityModule.class)
public abstract class HistoryActivityModule {

    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(final HistoryActivity activity);

    @Binds @PerActivity
    abstract HistoryActivityView bindHistoryActivityView(final HistoryActivity activity);

    @ContributesAndroidInjector(modules = HistoryFragmentModule.class)
    @PerFragment
    abstract HistoryFragment contributeHistoryFragment();
}
