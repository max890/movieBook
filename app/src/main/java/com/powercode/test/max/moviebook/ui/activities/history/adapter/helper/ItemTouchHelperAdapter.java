package com.powercode.test.max.moviebook.ui.activities.history.adapter.helper;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);

}
