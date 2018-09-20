package com.powercode.test.max.moviebook.ui.activities.details;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.databinding.ActivityDetailsBinding;
import com.powercode.test.max.moviebook.ui.activities.base.BaseActivity;
import com.powercode.test.max.moviebook.ui.activities.details.view.DetailsActivityView;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding> implements DetailsActivityView {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_details;
    }
}
