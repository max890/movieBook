package com.powercode.test.max.moviebook.app.di.modules;

import android.app.Application;
import android.content.Context;

import com.powercode.test.max.moviebook.app.MovieBook;
import com.powercode.test.max.moviebook.app.di.qualifiers.AppContext;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class, DbModule.class, RetrofitModule.class})
public abstract class AppModule {

    @Binds
    @Singleton
    abstract Application bindApplication(final MovieBook movieBook);

    @Binds
    @AppContext
    @Singleton
    abstract Context bindContext(final Application application);
}
