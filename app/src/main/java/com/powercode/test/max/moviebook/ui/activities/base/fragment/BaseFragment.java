package com.powercode.test.max.moviebook.ui.activities.base.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.BasePresenter;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.ViewModelFactory;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.view.MvpView;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<ViewInterface extends MvpView, Presenter extends BasePresenter<ViewInterface>, ViewBinding extends ViewDataBinding> extends DaggerFragment {

    private Handler mainHandler;

    protected Presenter presenter;

    protected ViewBinding binding;

    @Inject
    ViewModelFactory vmFactory;

    protected abstract Class<Presenter> getPresenterClass();

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = ViewModelProviders.of(this, vmFactory).get(getPresenterClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        onBindingReady(binding);
        return binding.getRoot();
    }

    protected void onBindingReady(@NonNull ViewBinding binding) {

    }

    protected final Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onAttachView((ViewInterface) this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDetachView();
    }

}
