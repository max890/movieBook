package com.powercode.test.max.moviebook.ui.activities.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.databinding.ActivityDetailsBinding;
import com.powercode.test.max.moviebook.ui.activities.base.BaseActivity;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.DetailsFragment;
import com.powercode.test.max.moviebook.ui.activities.details.view.DetailsActivityView;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding> implements DetailsActivityView {

    private static final String MOVIE_ID_PARAM = "com.powercode.test.max.moviebook.details.MOVIE_ID_PARAM";


    @Inject
    FragmentManager fragmentManager;

    public static void start(final Context context, String movieId) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(MOVIE_ID_PARAM, movieId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String movieId = getIntent().getStringExtra(MOVIE_ID_PARAM);
        fragmentManager.beginTransaction().add(R.id.details_fragment, DetailsFragment.build(movieId)).commit();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_details;
    }
}
