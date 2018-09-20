package com.powercode.test.max.moviebook.ui.activities.history.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.powercode.test.max.moviebook.databinding.ItemRecycleHistoryBinding;
import com.powercode.test.max.moviebook.model.entity.SearchHistoryModel;
import com.powercode.test.max.moviebook.ui.activities.history.adapter.helper.ItemTouchHelperAdapter;
import com.powercode.test.max.moviebook.ui.activities.history.adapter.helper.ItemTouchHelperViewHolder;
import com.powercode.test.max.moviebook.ui.activities.history.adapter.helper.OnStartDragListener;

import java.util.Collections;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder>
        implements ItemTouchHelperAdapter {

    private List<SearchHistoryModel> items = Collections.emptyList();
    private OnStartDragListener mDragStartListener;
    private HistoryAdapter.ViewHolderClickDelegate delegate;

    private int oldPosition;
    private int newPosition;


    public HistoryAdapter(OnStartDragListener mDragStartListener, HistoryAdapter.ViewHolderClickDelegate delegate) {
        this.mDragStartListener = mDragStartListener;
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecycleHistoryBinding binding = ItemRecycleHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new HistoryHolder(binding);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        SearchHistoryModel searchHistoryModel = items.get(position);
        holder.binding.setHolder(holder);
        holder.binding.setModel(searchHistoryModel);
        holder.binding.icon.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mDragStartListener.onStartDrag(holder);
            }
            return false;
        });
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

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        newPosition = toPosition;
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        notifyItemRemoved(position);
        if (delegate != null) {
            delegate.onDelete(items.get(position));
        }
        items.remove(position);
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

    public class HistoryHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

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

        @Override
        public void onItemSelected() {
            oldPosition = getAdapterPosition();
        }

        @Override
        public void onItemClear() {
            if (delegate != null && oldPosition != newPosition) {
                delegate.onMoved(items);
            }
        }
    }

    public interface ViewHolderClickDelegate {
        void onItemClick(SearchHistoryModel item);
        void onMoved(List<SearchHistoryModel> list);
        void onDelete(SearchHistoryModel item);
    }
}
