package com.teambazomi.mealplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addRecipe(View view) {
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);

    }
    public void myRecipes(View view) {
        Intent intent = new Intent(this, MyRecipes.class);
        startActivity(intent);

    }


    public void shoppingList(View view) {
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);

    }

    public void mealPlan(View view) {
        Intent intent = new Intent(this, MealPlan.class);
        startActivity(intent);

    }

}

