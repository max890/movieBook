package com.powercode.test.max.moviebook.ui.activities.history;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.databinding.ActivityHistoryBinding;
import com.powercode.test.max.moviebook.ui.activities.base.BaseActivity;
import com.powercode.test.max.moviebook.ui.activities.history.view.HistoryActivityView;

public class HistoryActivity extends BaseActivity<ActivityHistoryBinding> implements HistoryActivityView {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_history;
    }
}
