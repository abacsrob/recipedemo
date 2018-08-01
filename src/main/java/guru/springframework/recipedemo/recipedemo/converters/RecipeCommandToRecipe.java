package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.CategoryCommand;
import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Category;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }
        NoteCommandToNote noteCommandToNote = new NoteCommandToNote();
        CategoryCommandToCategory categoryCommandToCategory = new CategoryCommandToCategory();
        IngredientCommandToIngredient ingredientCommandToIngredient = new IngredientCommandToIngredient();
        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setSource(recipeCommand.getSource());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setNote(noteCommandToNote.convert(recipeCommand.getNote()));
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setImage(recipeCommand.getImage());
        Set<Category> categories = new HashSet<>();
        for (CategoryCommand categoryCommand: recipeCommand.getCategories()) {
            categories.add(categoryCommandToCategory.convert(categoryCommand));
        }
        recipe.setCategories(categories);
        Set<Ingredient> ingredients = new HashSet<>();
        for (IngredientCommand ingredientCommand: recipeCommand.getIngredients()) {
            ingredients.add(ingredientCommandToIngredient.convert(ingredientCommand));
        }
        recipe.setIngredients(ingredients);
        return recipe;
    }
}
