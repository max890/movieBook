package com.powercode.test.max.moviebook.ui.activities.history.fragment.presenter;

import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.presenter.BasePresenter;
import com.powercode.test.max.moviebook.ui.activities.history.fragment.view.HistoryFragmentView;

import java.util.List;

public abstract class HistoryFragmentPresenter extends BasePresenter<HistoryFragmentView> {

    public abstract void setup();

    public abstract void movePosition(List<SearchHistoryModel> list);

    public abstract void delete(SearchHistoryModel model);
}
