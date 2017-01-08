package com.teambazomi.mealplanner;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teambazomi.mealplanner.Ingredient;
import com.teambazomi.mealplanner.ItemTouchHelperAdapter;
import com.teambazomi.mealplanner.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by sol on 1/7/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<Ingredient> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView mTextView;

        private ViewHolder(View v) {
            super(v);
            mTextView = (TextView) itemView.findViewById(R.id.recipe_item);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    IngredientAdapter(List<Ingredient> ingredients) {
        mDataset = ingredients;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new IngredientAdapter.ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(IngredientAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // On swipe
    @Override
    public void onItemDismiss(int position) {
        // Load and delete recipe from database
        Ingredient.load(Ingredient.class, mDataset.get(position).getId()).delete();

        // Delete from list
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemSwipeEnd(int position) {
        // Don't do anything for now
    }

    // Drag and drop items
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mDataset, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mDataset, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}
