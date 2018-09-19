package com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry :
                    creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class: " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
