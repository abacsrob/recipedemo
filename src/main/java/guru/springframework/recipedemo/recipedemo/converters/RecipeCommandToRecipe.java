package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private CategoryCommandToCategory categoryConverter;
    private NoteCommandToNote noteConverter;
    private IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, NoteCommandToNote noteConverter, IngredientCommandToIngredient ingredientConverter) {
        this.categoryConverter = categoryConverter;
        this.noteConverter = noteConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }
        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setSource(recipeCommand.getSource());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        if (recipeCommand.getNote() != null) {
            recipe.setNote(noteConverter.convert(recipeCommand.getNote()));
        }
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setImage(recipeCommand.getImage());

        // standard
//        Set<Category> categories = new HashSet<>();
//        if (recipeCommand.getCategories() != null) {
//            for (CategoryCommand categoryCommand : recipeCommand.getCategories()) {
//                categories.add(categoryCommandToCategory.convert(categoryCommand));
//            }
//        }
//        recipe.setCategories(categories);

        // lambda
        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
            recipeCommand.getCategories()
                    .forEach(categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        // standard
//        Set<Ingredient> ingredients = new HashSet<>();
//        if (recipeCommand.getIngredients() != null) {
//            for (IngredientCommand ingredientCommand : recipeCommand.getIngredients()) {
//                ingredients.add(ingredientCommandToIngredient.convert(ingredientCommand));
//            }
//        }
//        recipe.setIngredients(ingredients);

        // lambda
        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
            recipeCommand.getIngredients()
                    .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

        return recipe;
    }
}
