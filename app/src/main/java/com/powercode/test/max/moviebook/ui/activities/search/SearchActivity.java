package com.powercode.test.max.moviebook.ui.activities.search;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.databinding.ActivitySearchBinding;
import com.powercode.test.max.moviebook.ui.activities.base.BaseActivity;
import com.powercode.test.max.moviebook.ui.activities.search.view.SearchActivityView;

public class SearchActivity extends BaseActivity<ActivitySearchBinding> implements SearchActivityView {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

}
