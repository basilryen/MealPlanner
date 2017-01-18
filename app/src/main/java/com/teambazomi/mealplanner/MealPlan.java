package com.teambazomi.mealplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

public class MealPlan extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RemovableItemListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        // List meals from database using RecyclerView
        List<Meal> meals = Meal.getAll();
        mRecyclerView = (RecyclerView) findViewById(R.id.meals_list);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new RemovableItemListAdapter(meals);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new RemovableItemTouchHelper(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

}
