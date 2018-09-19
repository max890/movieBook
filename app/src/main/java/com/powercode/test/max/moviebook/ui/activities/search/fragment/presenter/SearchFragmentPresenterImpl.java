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
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
                    .doOnSubscribe(__ -> view.showProgressBar())
                    .doOnTerminate(() -> view.hideProgressBar())
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
            Disposable disposable = Single.just("")
                    .subscribeOn(Schedulers.io())
                    .map(s -> movieDao.deleteAll())
                    .subscribe(rs -> {
                        page = 1;
                        movies.clear();
                        searchText = search;
                        loading = true;
                        runOnView(view -> {
                            view.setItems(movies);
                        }, true);
                        loadPage(search);
                    });
            getCompositeDisposable().add(disposable);

        }
    }

    private void loadPage(String search) {
        Disposable disposable = api.searchMovies(search, page)
                .compose(RxUtils.asyncSingle())
                .doOnSubscribe(__ -> view.showProgressBar())
                .doAfterTerminate(() -> view.hideProgressBar())
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
                }, throwable -> {
                    view.error(throwable.getMessage());
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
        loadPage(searchText);
    }

    public boolean isLoading() {
        return loading;
    }

    @Override
    public boolean isLastPage() {
        return lastPage;
    }
}
