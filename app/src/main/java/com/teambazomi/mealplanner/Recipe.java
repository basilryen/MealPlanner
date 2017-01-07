package com.teambazomi.mealplanner;

import java.util.ArrayList;
import java.util.List;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by basilryen on 1/2/17.
 */

@Table(name = "Recipes")
public class Recipe extends Model {
    public static int recid = 0;
    // Unique id given by server
    @Column(name = "remote_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long remoteId;

    // Regular fields
    @Column(name = "Title")
    public String title;
    @Column(name = "Description")
    String description;
    @Column(name = "ServingSize")
    int servingsize;

    // Association to Ingredients activeandroid model
    @Column(name = "IngredientsList", onUpdate = Column.ForeignKeyAction.CASCADE, onDelete = Column.ForeignKeyAction.CASCADE)
    List ingredients = new ArrayList();

//    String image_path;

    // default constructor
    public Recipe(){
        super();
    }

    public Recipe(int remoteId, String title, String description, int servingsize, List<Ingredient> ingredients){
        super();
        this.remoteId = remoteId;
        this.title = title;
        this.description = description;
        this.servingsize = servingsize;
        this.ingredients = ingredients;
    }

    // Method to get all Recipes
    public static List<Recipe> getAll() {
        return new Select()
                .from(Recipe.class)
                .execute();
    }

    public String toString() {
        return title + ": " + description;
    }

}
