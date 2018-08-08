package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
}
