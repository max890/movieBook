package com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter;

import android.arch.lifecycle.ViewModel;

import com.powercode.test.max.moviebook.ui.activities.base.fragment.view.MvpView;
import com.powercode.test.max.moviebook.ui.utils.Consumer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<View extends MvpView> extends ViewModel implements MvpPresenter {

    @Nullable
    protected View view;

    private final List<Consumer<View>> scheduledActions = new ArrayList<>();

    @Inject
    public BasePresenter() {
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void dispose(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void onAttachView(View v) {
        this.view = v;
        executeScheduledActions();
    }

    public void onDetachView() {
        this.view = null;
    }

    @Override
    protected void onCleared() {
        view = null;
        scheduledActions.clear();
        compositeDisposable.clear();
    }

    /**
     * Executes {@code action} on view if it's != null
     *
     * @param action Action that will be performed on view
     * @see #runOnView(Consumer, boolean)
     */
    protected void runOnView(Consumer<View> action) {
        runOnView(action, false);
    }

    /**
     * Executes {@code action} on view if it's != null. If view is null and {@code sticky} = true schedules {@code action} for executing when view is attached
     *
     * @param action Action that will be performed on view
     * @param sticky Indicates should action be scheduled if view is currently null
     */
    protected void runOnView(Consumer<View> action, boolean sticky) {
        if (view != null) {
            action.accept(view);
        } else if (sticky) {
            scheduledActions.add(action);
        }
    }

    private void executeScheduledActions() {
        if (view != null) {
            Iterator<Consumer<View>> iterator = scheduledActions.iterator();
            while (iterator.hasNext()) {
                Consumer<View> item = iterator.next();
                item.accept(view);
                iterator.remove();
            }
        }
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
