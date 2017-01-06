package com.teambazomi.mealplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

public class MyRecipes extends AppCompatActivity {

//    public static List recipes = new ArrayList();
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        // List recipes from database using RecyclerView
        List<Recipe> recipes = Recipe.getAll();
        mRecyclerView = (RecyclerView) findViewById(R.id.recipes_list);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter = new RecipeAdapter(recipes);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new RecyclerItemTouchHelper(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }
}
