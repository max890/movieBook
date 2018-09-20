package com.powercode.test.max.moviebook.ui.activities.history.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.powercode.test.max.moviebook.R;
import com.powercode.test.max.moviebook.databinding.FragmentHistoryBinding;
import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;
import com.powercode.test.max.moviebook.ui.activities.base.fragment.BaseFragment;
import com.powercode.test.max.moviebook.ui.activities.history.adapter.HistoryAdapter;
import com.powercode.test.max.moviebook.ui.activities.history.adapter.helper.OnStartDragListener;
import com.powercode.test.max.moviebook.ui.activities.history.adapter.helper.SimpleItemTouchHelperCallback;
import com.powercode.test.max.moviebook.ui.activities.history.fragment.presenter.HistoryFragmentPresenter;
import com.powercode.test.max.moviebook.ui.activities.history.fragment.view.HistoryFragmentView;
import com.powercode.test.max.moviebook.ui.activities.search.fragment.SearchFragment;

import java.util.List;

public class HistoryFragment extends BaseFragment<HistoryFragmentView, HistoryFragmentPresenter, FragmentHistoryBinding>
        implements HistoryFragmentView, HistoryAdapter.ViewHolderClickDelegate, OnStartDragListener {

    HistoryAdapter adapter;
    ItemTouchHelper mItemTouchHelper;

    @Override
    protected Class<HistoryFragmentPresenter> getPresenterClass() {
        return HistoryFragmentPresenter.class;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new HistoryAdapter(this, this);
        binding.recycler.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(binding.recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.setup();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_history;
    }

    @Override
    public void setItems(List<SearchHistoryModel> items) {
        adapter.swapItems(items);
    }

    @Override
    public void error(String error) {
        Snackbar.make(binding.getRoot(), error, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(SearchHistoryModel item) {
        Intent intent = new Intent();
        intent.putExtra(SearchFragment.HISTORY_SEARCH_PARAM, item.search);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();
    }

    @Override
    public void onMoved(List<SearchHistoryModel> list) {
        presenter.movePosition(list);
    }

    @Override
    public void onDelete(SearchHistoryModel item) {
        presenter.delete(item);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
