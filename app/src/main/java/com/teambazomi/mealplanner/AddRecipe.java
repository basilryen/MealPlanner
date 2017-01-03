package com.teambazomi.mealplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
    }

    // Populate "measurementType" drop-down spinner list with measurement types
    // measurement types: lb, oz, g, kg, cup, pint, quart, gallon, tsp, tbsp

    // When "Add Ingredient" button is clicked, save ingredient to list of ingredients

    // Populate "ingredients_list" with ingredients in current list of ingredients

    // When "Add Recipe" button is clicked, save recipe to MyRecipes.recipes()
}
