package com.powercode.test.max.moviebook.ui.activities.details.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.powercode.test.max.moviebook.databinding.ItemDetailsRatingBinding;
import com.powercode.test.max.moviebook.model.entity.RatingMovieModel;

import java.util.Collections;
import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingHolder> {

    List<RatingMovieModel> items = Collections.emptyList();

    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailsRatingBinding binding = ItemDetailsRatingBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new RatingHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingHolder holder, int position) {
        RatingMovieModel ratingModel = items.get(position);
        holder.binding.setHolder(holder);
        holder.binding.setModel(ratingModel);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void swapItems(List<RatingMovieModel> items) {
        final DiffCallback callback = new DiffCallback(this.items, items);
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        this.items = items;
        result.dispatchUpdatesTo(this);
    }

    private static class DiffCallback extends DiffUtil.Callback {

        private final List<RatingMovieModel> oldItems;
        private final List<RatingMovieModel> newItems;

        public DiffCallback(List<RatingMovieModel> oldItems, List<RatingMovieModel> newItems) {
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
            return oldItemPosition == newItemPosition;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItems.get(oldItemPosition).hashCode() == newItems.get(newItemPosition).hashCode();
        }

    }

    public class RatingHolder extends RecyclerView.ViewHolder {

        ItemDetailsRatingBinding binding;

        public RatingHolder(ItemDetailsRatingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
