package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import org.springframework.data.domain.Sort;

import java.util.Set;
import java.util.SortedSet;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findRecipeCommandById(Long id);

    void deleteRecipe(Long id);

    SortedSet<Recipe> getSortedRecipes(Sort sort);
}
