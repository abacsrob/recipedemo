package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    public RecipeCommand findRecipeCommandById(Long id);

    public void deleteRecipe(Long id);
}
