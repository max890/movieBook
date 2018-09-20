package com.powercode.test.max.moviebook.ui.activities.details.fragment;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.databinding.FragmentDetailsBinding;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.BaseFragment;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.presenter.DetailsFragmentPresenter;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.view.DetailsFragmentView;

public class DetailsFragment extends BaseFragment<DetailsFragmentView, DetailsFragmentPresenter, FragmentDetailsBinding> implements DetailsFragmentView{
    @Override
    protected Class<DetailsFragmentPresenter> getPresenterClass() {
        return DetailsFragmentPresenter.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_details;
    }
}
