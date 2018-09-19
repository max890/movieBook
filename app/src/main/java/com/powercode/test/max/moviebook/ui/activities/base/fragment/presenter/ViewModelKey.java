package com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter;

import android.arch.lifecycle.ViewModel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MapKey
@Documented
public @interface ViewModelKey {

    Class<? extends ViewModel> value();

}
