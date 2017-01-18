package com.teambazomi.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class AddRecipe extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RemovableItemListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    Button addIngredientButton;
    EditText name;
    EditText amount;
    Spinner type;
    ListView lv;
    List<Ingredient> ings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // Populate "measurementType" drop-down spinner list with measurement types
        Spinner measurement_dropdown = (Spinner) findViewById(R.id.measurementType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.measurements_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measurement_dropdown.setAdapter(adapter);

        // Set up for RecyclerView list
        mRecyclerView = (RecyclerView) findViewById(R.id.ingredients_list);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        ings = Ingredient.getAllForRecipe(Recipe.recid);
        mAdapter = new RemovableItemListAdapter(ings);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new RemovableItemTouchHelper(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }

    // When "Add Ingredient" button is clicked, save ingredient to list of ingredients
    public void addIngredient(View view){
        // Get values for ingredient name, amount and measurement type
        addIngredientButton = (Button) findViewById(R.id.add_ingredient);
        name = (EditText) findViewById(R.id.ingredient);
        amount = (EditText) findViewById(R.id.amount);
        type = (Spinner) findViewById(R.id.measurementType);
        String nameTemp = name.getText().toString();
        int amountTemp = Integer.parseInt(amount.getText().toString());
        String typeTemp = type.getSelectedItem().toString();

        // Create new Ingredient and save to ingredients
        Ingredient temp = new Ingredient();
        temp.remoteId = Ingredient.ingid;
        temp.recipeId = Recipe.recid;
        Ingredient.ingid++;
        temp.name = nameTemp;
        temp.amount = amountTemp;
        temp.measurementType = typeTemp;
        temp.save();

        // Trying to update view to show new recipe, but this is not working yet!!
        ings = Ingredient.getAllForRecipe(Recipe.recid);
        mAdapter.notifyItemChanged(ings.size(), ings);
       // mAdapter.notifyDataSetChanged();
    }

    // When "Add Recipe" button is clicked, save recipe to MyRecipes.recipes()
    public void addNewRecipe(View view){
        EditText recipe_name = (EditText) findViewById(R.id.title);
        EditText description = (EditText) findViewById(R.id.recipe_instructions);
        String recTemp = recipe_name.getText().toString();
        String desTemp = description.getText().toString();

        // Create new Recipe and save to MyRecipes
        Recipe temp = new Recipe();
        temp.remoteId = Recipe.recid;
        Recipe.recid++;
        temp.title = recTemp;
        temp.description = desTemp;
        temp.ingredients = ings;
        temp.save();
    }

    // When "My Recipes" button is clicked, open MyRecipes activity
    public void myRecipes(View view) {
        Intent intent = new Intent(this, MyRecipes.class);
        startActivity(intent);
    }

}
