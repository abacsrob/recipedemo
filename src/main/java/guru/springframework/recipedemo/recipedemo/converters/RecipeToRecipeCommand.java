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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
        NoteToNoteCommand noteToNoteCommand = new NoteToNoteCommand();
        IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand();
        CategoryToCategoryCommand categoryToCategoryCommand =  new CategoryToCategoryCommand();
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setNote(noteToNoteCommand.convert(recipe.getNote()));
        Set<IngredientCommand> ingredients = new HashSet<>();
        for (Ingredient ingredient: recipe.getIngredients()) {
            ingredients.add(ingredientToIngredientCommand.convert(ingredient));
        }
        recipeCommand.setIngredients(ingredients);
        Set<CategoryCommand> categories = new HashSet<>();
        for (Category category: recipe.getCategories()) {
            categories.add(categoryToCategoryCommand.convert(category));
        }
        return recipeCommand;
    }
}
