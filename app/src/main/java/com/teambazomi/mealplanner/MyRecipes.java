package com.teambazomi.mealplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyRecipes extends AppCompatActivity {

    public static List recipes = new ArrayList();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        // Populate "ingredients_list" with reciipes in current list of ingredients
        lv = (ListView) findViewById(R.id.recipe_list);
        ArrayAdapter<Object> arrayAdapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, recipes);
        lv.setAdapter(arrayAdapter);
    }
}
