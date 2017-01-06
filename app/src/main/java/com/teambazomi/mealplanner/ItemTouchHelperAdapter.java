package com.teambazomi.mealplanner;

/**
 * Created by sol on 1/5/2017.
 * Thanks & credit to https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-b9456d2b1aaf#.ndllchrdz
 */

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
