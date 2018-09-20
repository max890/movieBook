package com.powercode.test.max.moviebook.ui.activities.search.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.app.utils.NetworkUtils;
import com.powercode.test.max.moviebook.databinding.FragmentSearchBinding;
import com.powercode.test.max.moviebook.model.entity.ShortMovieModel;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.BaseFragment;
import com.powercode.test.max.moviebook.ui.activities.history.HistoryActivity;
import com.powercode.test.max.moviebook.ui.activities.search.adapter.MovieAdapter;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.presenter.SearchFragmentPresenter;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.view.SearchFragmentView;

import java.util.List;

public class SearchFragment extends BaseFragment<SearchFragmentView, SearchFragmentPresenter, FragmentSearchBinding>
        implements SearchFragmentView, MovieAdapter.ViewHolderClickDelegate {


    private static final int REQUEST_CODE_HISTORY = 100;
    public static final String HISTORY_SEARCH_PARAM = "com.powercode.test.max.moviebook.search.HISTORY_SEARCH_PARAM";


    MovieAdapter adapter;
    public SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    protected Class<SearchFragmentPresenter> getPresenterClass() {
        return SearchFragmentPresenter.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MovieAdapter(this);
        binding.setView(this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = binding.recycler.getLayoutManager();

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();

                if (!presenter.isLoading() && !presenter.isLastPage()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        presenter.nextPage();
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        presenter.setup();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            queryTextListener = new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchView.clearFocus();
                    searchView.onActionViewCollapsed();
                    adapter.clearItems();
                    binding.foundResult.setText(R.string.not_found);
                    if (NetworkUtils.isNetworkConnected(getContext())) {
                        presenter.search(query);
                    } else {
                        Snackbar.make(binding.rootLayout, R.string.disable_network, Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.retry, (view) -> presenter.search(query)).show();
                    }
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setItems(List<ShortMovieModel> items) {
        adapter.swapItems(items);
        binding.foundResult.setText(items.size() == 1 ? getString(R.string.search_result_found_one) : getString(R.string.search_result_found_plural, items.size()));
    }

    @Override
    public void error(String error) {
        if (NetworkUtils.isNetworkConnected(getContext())) {
            Snackbar.make(binding.rootLayout, error, Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(binding.rootLayout, R.string.disable_network, Snackbar.LENGTH_INDEFINITE)
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
    public void openHistory() {
        Log.d("SearchFragment", "Open History");
        Intent intent = new Intent(getContext(), HistoryActivity.class);
        startActivityForResult(intent, REQUEST_CODE_HISTORY);
    }

    @Override
    public void onItemClick(ShortMovieModel item) {
        Log.d("SearchFragment", "Item click: " + item.Title);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_HISTORY
                && resultCode == Activity.RESULT_OK) {
            String search = data.getStringExtra(HISTORY_SEARCH_PARAM);
            presenter.search(search);
        }
    }
}
