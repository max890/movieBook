package com.powercode.test.max.moviebook.app.di;

import com.powercode.test.max.moviebook.app.MovieBook;
import com.powercode.test.max.moviebook.app.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent extends AndroidInjector<MovieBook> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MovieBook> {}
}
