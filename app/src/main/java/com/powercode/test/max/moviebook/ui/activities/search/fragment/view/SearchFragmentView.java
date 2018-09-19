package com.powercode.test.max.moviebook.ui.activities.search.fragment.view;

import com.powercode.test.max.moviebook.model.entity.ShortMovieModel;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.view.MvpView;

import java.util.List;

public interface SearchFragmentView extends MvpView {

    void setItems(List<ShortMovieModel> items);

    void error(String error);

    void showProgressBar();

    void hideProgressBar();

    void openHistory();
}
