package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {
    private NoteToNoteCommand noteConverter;
    private IngredientToIngredientCommand ingredientConverter;
    private CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NoteToNoteCommand noteConverter, IngredientToIngredientCommand ingredientConverter, CategoryToCategoryCommand categoryConverter) {
        this.noteConverter = noteConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }
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
        recipeCommand.setNote(noteConverter.convert(recipe.getNote()));

        // standard
//        Set<IngredientCommand> ingredients = new HashSet<>();
//        for (Ingredient ingredient: recipe.getIngredients()) {
//            ingredients.add(ingredientConverter.convert(ingredient));
//        }
//        recipeCommand.setIngredients(ingredients);

        // lambda
        if (recipe.getIngredients().size() > 0) {
            recipe.getIngredients()
                    .forEach(_recipe -> recipeCommand.getIngredients().add(ingredientConverter.convert(_recipe)));
        }

        //standard
//        Set<CategoryCommand> categories = new HashSet<>();
//        for (Category category: recipe.getCategoryCommands()) {
//            categories.add(categoryConverter.convert(category));
//        }
//        recipeCommand.setCategories(categories);

        // lambda
        if (recipe.getCategories().size() > 0) {
            recipe.getCategories()
                    .forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }
        return recipeCommand;
    }
}
