package com.powercode.test.max.moviebook.ui.activities.history.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.powercode.test.max.moviebook.databinding.ItemRecycleHistoryBinding;
import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;

import java.util.Collections;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder> {

    private List<SearchHistoryModel> items = Collections.emptyList();

    @Nullable
    private HistoryAdapter.ViewHolderClickDelegate delegate;

    public HistoryAdapter(HistoryAdapter.ViewHolderClickDelegate delegate) {
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecycleHistoryBinding binding = ItemRecycleHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new HistoryAdapter.HistoryHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        SearchHistoryModel searchHistoryModel = items.get(position);
        holder.binding.setHolder(holder);
        holder.binding.setModel(searchHistoryModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swapItems(List<SearchHistoryModel> items) {
        final DiffCallback callback = new DiffCallback(this.items, items);
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        this.items = items;
        result.dispatchUpdatesTo(this);
    }

    private static class DiffCallback extends DiffUtil.Callback {

        private final List<SearchHistoryModel> oldItems;
        private final List<SearchHistoryModel> newItems;

        public DiffCallback(List<SearchHistoryModel> oldItems, List<SearchHistoryModel> newItems) {
            this.oldItems = oldItems;
            this.newItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return oldItems.size();
        }

        @Override
        public int getNewListSize() {
            return newItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).id == newItems.get(newItemPosition).id;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).hashCode() == newItems.get(newItemPosition).hashCode();
        }

    }

    public class HistoryHolder extends RecyclerView.ViewHolder {

        ItemRecycleHistoryBinding binding;

        public HistoryHolder(ItemRecycleHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onItemClick() {
            if (delegate != null) {
                delegate.onItemClick(items.get(getAdapterPosition()));
            }
        }
    }

    public interface ViewHolderClickDelegate {
        void onItemClick(SearchHistoryModel item);
    }
}
