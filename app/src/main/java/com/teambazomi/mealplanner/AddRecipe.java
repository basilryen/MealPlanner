package com.teambazomi.mealplanner;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class AddRecipe extends Fragment {

    private RecyclerView mRecyclerView;
    private RemovableItemListAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    Button addRecipeButton;
    Button addIngredientButton;

    EditText name;
    EditText amount;
    Spinner type;
    ListView lv;
    List<Ingredient> ings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_recipe, container, false);

        addIngredientButton = (Button) view.findViewById(R.id.add_ingredient);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredient();
            }
        });

        addRecipeButton = (Button) view.findViewById(R.id.add_recipe);
        addRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewRecipe();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();
        // Populate "measurementType" drop-down spinner list with measurement types
        Spinner measurement_dropdown = (Spinner) v.findViewById(R.id.measurementType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.measurements_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measurement_dropdown.setAdapter(adapter);

        // Set up for RecyclerView list
        mRecyclerView = (RecyclerView) v.findViewById(R.id.ingredients_list);
        mRecyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        ings = Ingredient.getAllForRecipe(Recipe.recid);
        mAdapter = new RemovableItemListAdapter(ings);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new RemovableItemTouchHelper(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }

    // When "Add Ingredient" button is clicked, save ingredient to list of ingredients
    public void addIngredient(){
        View v = getView();
        // Get values for ingredient name, amount and measurement type
        addIngredientButton = (Button) v.findViewById(R.id.add_ingredient);
        name = (EditText) v.findViewById(R.id.ingredient);
        amount = (EditText) v.findViewById(R.id.amount);
        type = (Spinner) v.findViewById(R.id.measurementType);
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

        // Trying to update view to show new ingredients, but this is not working yet!!
        ings = Ingredient.getAllForRecipe(Recipe.recid);
        mAdapter.notifyItemChanged(ings.size(), ings);
        //mAdapter.notifyDataSetChanged();
    }

    // When "Add Recipe" button is clicked, save recipe to MyRecipes.recipes()
    public void addNewRecipe(){
        View v = getView();

        EditText recipe_name = (EditText) v.findViewById(R.id.title);
        EditText description = (EditText) v.findViewById(R.id.recipe_instructions);
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

}
