package com.powercode.test.max.moviebook.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.powercode.test.max.moviebook.app.di.DaggerAppComponent;

public class MovieBook extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        DaggerAppComponent.builder()
                .create(this)
                .inject(this);
    }
}
