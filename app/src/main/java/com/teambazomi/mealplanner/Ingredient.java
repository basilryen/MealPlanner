package com.teambazomi.mealplanner;

/**
 * Created by basilryen on 1/2/17.
 */

public class Ingredient {
    String name;
    int amount;
    String measurementType;

    public Ingredient(String name, int amount, String measurementType){
        this.name = name;
        this.amount = amount;
        this.measurementType = measurementType;
    }

    @Override
    public String toString() {
        String prettyIngredient = name + ", " + amount + " " + measurementType;
        return prettyIngredient;
    }
}
