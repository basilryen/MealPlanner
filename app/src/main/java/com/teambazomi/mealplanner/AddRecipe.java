package com.teambazomi.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddRecipe extends AppCompatActivity {

    List ingredients = new ArrayList();
    Button addIngredientButton;
    EditText name;
    EditText amount;
    Spinner type;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        // Populate "measurementType" drop-down spinner list with measurement types
        Spinner measurement_dropdown = (Spinner) findViewById(R.id.measurementType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.measurements_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        measurement_dropdown.setAdapter(adapter);

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
        Ingredient temp = new Ingredient(nameTemp, amountTemp, typeTemp);
        ingredients.add(temp);

        // Populate "ingredients_list" with ingredients in current list of ingredients
        lv = (ListView) findViewById(R.id.ingredients_list);
        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, ingredients);
        lv.setAdapter(arrayAdapter);
    }

    // When "Add Recipe" button is clicked, save recipe to MyRecipes.recipes()
    public void addNewRecipe(View view){
        EditText recipe_name = (EditText) findViewById(R.id.title);
        EditText description = (EditText) findViewById(R.id.recipe_instructions);
        String recTemp = recipe_name.getText().toString();
        String desTemp = description.getText().toString();

        // Create new Ingredient and save to ingredients
        Recipe temp = new Recipe(recTemp, desTemp, ingredients);
        MyRecipes.recipes.add(temp);


    }

    public void myRecipes(View view) {
        Intent intent = new Intent(this, MyRecipes.class);
        startActivity(intent);

    }

}
