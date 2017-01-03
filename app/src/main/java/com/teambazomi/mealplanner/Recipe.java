package com.teambazomi.mealplanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by basilryen on 1/2/17.
 */

public class Recipe {

    String title;
    String description;
    List ingredients = new ArrayList();

    public Recipe(String title, String description, List ingredients){
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
    }

}
