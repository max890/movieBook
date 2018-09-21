package com.powercode.test.max.moviebook.ui.activities.details.fragment.view;

import com.powercode.test.max.moviebook.model.entity.DetailsMovieModel;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.view.MvpView;

public interface DetailsFragmentView extends MvpView {

    void showMovie(DetailsMovieModel movieModel);

    void error(String error);

    void showProgressBar();

    void hideProgressBar();

    void clickPlot();
}
