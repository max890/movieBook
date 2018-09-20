package com.powercode.test.max.moviebook.ui.activities.history.fragment.presenter;

import com.powercode.test.max.moviebook.model.db.dao.HistoryMovieDao;
import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;
import com.powercode.test.max.moviebook.model.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

public class HistoryFragmentPresenterImpl extends HistoryFragmentPresenter {


    @Inject
    HistoryMovieDao historyMovieDao;

    List<SearchHistoryModel> historyList;

    @Inject
    public HistoryFragmentPresenterImpl() {
    }

    @Override
    public void setup() {
        if (historyList == null) {
            historyList = new ArrayList<>();
            loadData();
        } else {
            runOnView(item -> item.setItems(historyList), true);
        }
    }

    @Override
    public void movePosition(List<SearchHistoryModel> list) {
        Disposable disposable = Observable.just(list)
                .map(searchHistoryModels -> {
                    for (int i = 0; i < searchHistoryModels.size(); i++) {
                        SearchHistoryModel item = searchHistoryModels.get(i);
                        item.position = i + 1;
                        historyMovieDao.insert(item);
                    }
                   return searchHistoryModels;
                })
                .compose(RxUtils.asyncObservable())
                .subscribe(searchHistoryModels -> {
                    runOnView(item -> item.setItems(searchHistoryModels), true);
                }, throwable -> runOnView(item -> item.error(throwable.getMessage()), true));
        getCompositeDisposable().add(disposable);
    }

    @Override
    public void delete(SearchHistoryModel model) {
        Disposable disposable = Single.just(model)
                .map(historyModel -> {
                    return historyMovieDao.delete(historyModel);
                })
                .compose(RxUtils.asyncSingle())
                .subscribe();
        getCompositeDisposable().add(disposable);
    }

    private void loadData() {
        Disposable disposable = Observable.just(historyList)
                .map(s -> historyMovieDao.getHistories())
                .compose(RxUtils.asyncObservable())
                .takeLast(20)
                .subscribe(searchHistoryModels -> {
                    this.historyList = searchHistoryModels;
                    runOnView(item -> item.setItems(searchHistoryModels), true);
                }, throwable -> runOnView(item -> item.error(throwable.getMessage()), true));
        getCompositeDisposable().add(disposable);

    }
}
