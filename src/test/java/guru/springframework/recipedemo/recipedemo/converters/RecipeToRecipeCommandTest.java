package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Category;
import guru.springframework.recipedemo.recipedemo.domain.Difficulty;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    RecipeToRecipeCommand recipeConverter;

    @Before
    public void setUp() throws Exception {
        NoteToNoteCommand noteConverter = new NoteToNoteCommand();
        IngredientToIngredientCommand ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        CategoryToCategoryCommand categoryConverter = new CategoryToCategoryCommand();
        recipeConverter = new RecipeToRecipeCommand(noteConverter, ingredientConverter, categoryConverter);
    }

    @Test
    public void testNull() {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(recipeConverter.convert(new Recipe()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        Integer prepTime = 30;
        Integer cookTime = 20;
        Integer source = 10;
        Integer servings = 4;
        Byte[] image = new Byte[]{};
        String description = "description";
        String directions = "directions";
        Difficulty difficulty = Difficulty.EASY;
        Set<Ingredient> ingredients = new HashSet<>();
        Set<Category> categories = new HashSet<>();

        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setImage(image);
        recipe.setServings(servings);
        recipe.setPrepTime(prepTime);
        recipe.setCookTime(cookTime);
        recipe.setSource(source);
        recipe.setImage(image);
        recipe.setDescription(description);
        recipe.setDirections(directions);
        recipe.setDifficulty(difficulty);
        recipe.setIngredients(ingredients);
        recipe.setCategories(categories);

        RecipeCommand recipeCommand = recipeConverter.convert(recipe);
        assertNotNull(recipeCommand);
        assertEquals(recipeCommand.getId(), id);
        assertEquals(recipeCommand.getPrepTime(), prepTime);
        assertEquals(recipeCommand.getCookTime(), cookTime);
        assertEquals(recipeCommand.getSource(), source);
        assertEquals(recipeCommand.getServings(), servings);
        assertEquals(recipeCommand.getDescription(), description);
        assertEquals(recipeCommand.getDirections(), directions);
        assertEquals(recipeCommand.getDifficulty(), difficulty);
    }
}
