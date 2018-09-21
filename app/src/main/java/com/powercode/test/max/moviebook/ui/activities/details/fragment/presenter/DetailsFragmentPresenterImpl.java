package com.powercode.test.max.moviebook.ui.activities.details.fragment.presenter;

import com.powercode.test.max.moviebook.model.api.SearchMovieApi;
import com.powercode.test.max.moviebook.model.entity.DetailsMovieModel;
import com.powercode.test.max.moviebook.model.utils.RxUtils;
import com.powercode.test.max.moviebook.ui.activities.details.fragment.view.DetailsFragmentView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class DetailsFragmentPresenterImpl extends DetailsFragmentPresenter {

    @Inject
    SearchMovieApi movieApi;

    private DetailsMovieModel movieModel;
    private String movieId;


    @Inject
    public DetailsFragmentPresenterImpl() {
    }

    @Override
    public void init(String movieId) {
        this.movieId = movieId;
        if (movieModel == null) {
            load(movieId);
        } else {
            runOnView(item -> item.showMovie(movieModel), true);
        }
    }

    @Override
    public void retry() {
        load(movieId);
    }

    private void load(String movieId) {
        Disposable disposable = movieApi.getDetailsMovie(movieId)
                .doOnSubscribe(__ -> runOnView(DetailsFragmentView::showProgressBar))
                .doOnTerminate(() -> runOnView(DetailsFragmentView::hideProgressBar))
                .compose(RxUtils.asyncObservable())
                .subscribe(model -> {
                            this.movieModel = model;
                            runOnView(item -> item.showMovie(model), true);
                        },
                        throwable -> runOnView(item -> item.error(throwable.getMessage())));
        getCompositeDisposable().add(disposable);
    }
}
