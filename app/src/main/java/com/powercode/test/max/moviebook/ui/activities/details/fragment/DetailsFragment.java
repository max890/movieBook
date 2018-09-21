package com.powercode.test.max.moviebook.ui.activities.details.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.app.utils.NetworkUtils;
import com.powercode.test.max.moviebook.databinding.FragmentDetailsBinding;
import com.powercode.test.max.moviebook.model.entity.DetailsMovieModel;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.BaseFragment;
import com.powercode.test.max.moviebook.ui.activities.details.adapter.RatingAdapter;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.presenter.DetailsFragmentPresenter;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.view.DetailsFragmentView;

public class DetailsFragment extends BaseFragment<DetailsFragmentView, DetailsFragmentPresenter, FragmentDetailsBinding> implements DetailsFragmentView{

    private static final String MOVIE_ID_PARAM = "com.powercode.test.max.moviebook.details.fragment.MOVIE_ID_PARAM";

    RatingAdapter adapter;

    @Override
    protected Class<DetailsFragmentPresenter> getPresenterClass() {
        return DetailsFragmentPresenter.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_details;
    }

    public static DetailsFragment build(String movieId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(MOVIE_ID_PARAM, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init(getArguments().getString(MOVIE_ID_PARAM));
        binding.setView(this);
        adapter = new RatingAdapter();
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void showMovie(DetailsMovieModel movieModel) {
        binding.setMovie(movieModel);
        adapter.swapItems(movieModel.Ratings);
    }

    @Override
    public void error(String error) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            Snackbar.make(binding.getRoot(), error, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(binding.getRoot(), R.string.disable_network, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry, (view) -> presenter.retry()).show();
        }
    }

    @Override
    public void showProgressBar() {
        binding.setProgressBar(true);
    }

    @Override
    public void hideProgressBar() {
        binding.setProgressBar(false);
    }

    @Override
    public void clickPlot() {
        binding.setPlotVisible(!binding.getPlotVisible());
    }
}
