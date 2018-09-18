package com.powercode.test.max.moviebook.ui.activities.search.fragment.presenter;

import android.text.TextUtils;

import com.powercode.test.max.moviebook.model.api.SearchMovieApi;
import com.powercode.test.max.moviebook.model.db.dao.ShortMovieDao;
import com.powercode.test.max.moviebook.model.entity.ShortMovieModel;
import com.powercode.test.max.moviebook.model.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SearchFragmentPresenterImpl extends SearchFragmentPresenter {


    @Inject
    SearchMovieApi api;

    @Inject
    ShortMovieDao movieDao;

    private List<ShortMovieModel> movies;
    private int page;
    private boolean loading;
    private boolean lastPage;
    private String searchText;

    @Inject
    SearchFragmentPresenterImpl() {
    }

    @Override
    public void setup() {
        if (movies == null) {
            movies = new ArrayList<ShortMovieModel>();
            Disposable disposable = Observable.just(movies)
                    .map(shortMovieModels -> {
                        return movieDao.getMovies();
                    })
                    .compose(RxUtils.asyncObservable())
                    .subscribe(detailsMovieModels -> runOnView(view -> {
                        view.setItems(detailsMovieModels);
                    }, true));
            getCompositeDisposable().add(disposable);
        } else {
            runOnView(view -> {
                view.setItems(movies);
            }, true);
        }
    }

    @Override
    public void search(String search) {
        if (!TextUtils.isEmpty(search.trim())) {
            page = 1;
            movies.clear();
            searchText = search;
            loading = true;
            runOnView(view -> {
                view.setItems(movies);
            }, true);
            loadPage(search);
        }
    }

    private void loadPage(String search) {
        Disposable disposable = api.searchMovies(search, page)
                .compose(RxUtils.asyncSingle())
                .subscribe(searchMovieModel -> {
                    if (!searchMovieModel.getResponse()) {
                        runOnView(item -> {
                            item.error(searchMovieModel.getError());
                        }, true);
                    } else {
                        this.movies.addAll(searchMovieModel.getSearch());
                        loading = false;
                        lastPage = (searchMovieModel.getTotalResults() - movies.size()) % 10 < 1;
                        runOnView(item -> {
                            item.setItems(this.movies);
                        }, true);
                        Observable.fromIterable(movies)
                                .map(shortMovieModel -> movieDao.insert(shortMovieModel))
                                .compose(RxUtils.asyncObservable())
                                .subscribe();
                    }
                });

        getCompositeDisposable().add(disposable);
    }

    @Override
    public void nextPage() {
        if (!loading && !TextUtils.isEmpty(searchText)) {
            loading = true;
            page++;
            loadPage(searchText);
        }
    }

    @Override
    public void retry() {

    }

    public boolean isLoading() {
        return loading;
    }

    @Override
    public boolean isLastPage() {
        return lastPage;
    }
}
