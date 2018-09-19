package com.powercode.test.max.moviebook.ui.activities.history.fragment.view;

import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.view.MvpView;

import java.util.List;

public interface HistoryFragmentView extends MvpView {

    void setItems(List<SearchHistoryModel> items);

    void error(String error);

}
