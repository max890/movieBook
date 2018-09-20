package com.powercode.test.max.moviebook.ui.activities.search.fragment.presenter;

import android.text.TextUtils;

import com.powercode.test.max.moviebook.model.api.SearchMovieApi;
import com.powercode.test.max.moviebook.model.db.dao.HistoryMovieDao;
import com.powercode.test.max.moviebook.model.db.dao.ShortMovieDao;
import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;
import com.powercode.test.max.moviebook.model.entity.ShortMovieModel;
import com.powercode.test.max.moviebook.model.utils.RxUtils;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.view.SearchFragmentView;

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
    @Inject
    HistoryMovieDao historyMovieDao;

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
                    .doOnSubscribe(__ -> runOnView(SearchFragmentView::showProgressBar, true))
                    .doOnTerminate(() -> runOnView(SearchFragmentView::hideProgressBar, true))
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
                .doOnSubscribe(__ -> runOnView(SearchFragmentView::showProgressBar, true))
                .doAfterTerminate(() -> runOnView(SearchFragmentView::hideProgressBar, true))
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
                    Observable.just(search)
                            .map(s -> {
                                int size = historyMovieDao.getSize();
                                if (size >= 20) {
                                    Observable.fromIterable(historyMovieDao.getHistories())
                                            .map(historyModel -> {
                                                if (historyModel.position == 1) {
                                                    historyMovieDao.delete(historyModel);
                                                }
                                                return historyModel;
                                            })
                                            .filter(historyModel -> historyModel.position != 1)
                                            .map(historyModel -> {
                                                historyModel.position = historyModel.position - 1;
                                                return historyModel;
                                            })
                                            .toList()
                                            .map(searchHistoryModels -> {
                                                SearchHistoryModel searchHistoryModel = new SearchHistoryModel();
                                                searchHistoryModel.search = search;
                                                searchHistoryModel.timestamp = System.currentTimeMillis();
                                                searchHistoryModel.position = 20;
                                                searchHistoryModels.add(searchHistoryModel);
                                                historyMovieDao.insertAll(searchHistoryModels);
                                                return searchHistoryModels;
                                            }).subscribe();
                                } else {
                                    Observable.just(search)
                                            .map(s1 -> {
                                                SearchHistoryModel searchHistoryModel = new SearchHistoryModel();
                                                searchHistoryModel.search = search;
                                                searchHistoryModel.timestamp = System.currentTimeMillis();
                                                searchHistoryModel.position = historyMovieDao.getMaxPosition() + 1;
                                                return historyMovieDao.insert(searchHistoryModel);
                                            }).subscribe();

                                }
                                return s;
                            })
                            .subscribeOn(Schedulers.io())
                            .subscribe();
                }, throwable -> {
                    runOnView(view -> view.error(throwable.getMessage()), true);
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
