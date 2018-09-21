package com.powercode.test.max.moviebook.ui.activities.details.fragment.presenter;

import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.BasePresenter;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.view.DetailsFragmentView;

public abstract class DetailsFragmentPresenter extends BasePresenter<DetailsFragmentView> {

    public abstract void init(String movieId);

    public abstract void retry();
}
