package com.powercode.test.max.moviebook.ui.activities.search.fragment.presenter;

import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.BasePresenter;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.view.SearchFragmentView;

public abstract class SearchFragmentPresenter extends BasePresenter<SearchFragmentView> {

    public abstract void setup();
    public abstract void search(String search);
    public abstract void nextPage();
    public abstract void retry();
    public abstract boolean isLoading();
    public abstract boolean isLastPage();

}
