package com.powercode.test.max.moviebook.ui.activities.search.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.powercode.test.max.moviebook.databinding.ItemMovieBinding;
import com.powercode.test.max.moviebook.model.entity.ShortMovieModel;

import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ShortMovieHolder> {

    private List<? extends ShortMovieModel> items = Collections.emptyList();

    @NonNull
    @Override
    public ShortMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()),
            parent, false);

        return new ShortMovieHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortMovieHolder holder, int position) {
        ShortMovieModel model = items.get(position);
        holder.binding.setMovie(model);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void swapItems(List<? extends ShortMovieModel> items) {
        final DiffCallback callback = new DiffCallback(this.items, items);
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        this.items = items;
        result.dispatchUpdatesTo(this);
    }

    public void clearItems() {
        notifyItemRangeRemoved(0, this.items.size());
        this.items.clear();
    }

    private static class DiffCallback extends DiffUtil.Callback {

        private final List<? extends ShortMovieModel> oldItems;
        private final List<? extends ShortMovieModel> newItems;

        public DiffCallback(List<? extends ShortMovieModel> oldItems, List<? extends ShortMovieModel> newItems) {
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
            return oldItems.get(oldItemPosition).id.equals(newItems.get(newItemPosition).id);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).hashCode() == newItems.get(newItemPosition).hashCode();
        }

    }

    public class ShortMovieHolder extends RecyclerView.ViewHolder {

        ItemMovieBinding binding;

        public ShortMovieHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onMovieClick() {

        }
    }
}
