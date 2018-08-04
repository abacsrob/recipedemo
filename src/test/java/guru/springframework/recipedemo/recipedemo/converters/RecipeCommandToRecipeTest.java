package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.commands.NoteCommand;
import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.commands.UnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.domain.Difficulty;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import guru.springframework.recipedemo.recipedemo.domain.Note;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {
    
    RecipeCommandToRecipe recipeConverter;

    @Before
    public void setUp() throws Exception {
        CategoryCommandToCategory categoryConverter = new CategoryCommandToCategory();
        NoteCommandToNote noteConverter = new NoteCommandToNote();
        IngredientCommandToIngredient ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        recipeConverter = new RecipeCommandToRecipe(categoryConverter, noteConverter, ingredientConverter);
    }

    @Test
    public void testNull() {
        assertNull(recipeConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(recipeConverter.convert(new RecipeCommand()));
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
        BigDecimal amount = BigDecimal.ONE;

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(id);
        recipeCommand.setImage(image);
        recipeCommand.setServings(servings);
        recipeCommand.setPrepTime(prepTime);
        recipeCommand.setCookTime(cookTime);
        recipeCommand.setSource(source);
        recipeCommand.setImage(image);
        recipeCommand.setDescription(description);
        recipeCommand.setDirections(directions);
        recipeCommand.setDifficulty(difficulty);
        NoteCommand noteCommand = new NoteCommand();
        noteCommand.setId(id);
        noteCommand.setNotes(description);
        recipeCommand.setNote(noteCommand);
        Set<IngredientCommand> ingredients = new HashSet<>();
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(id);
        ingredientCommand.setDescription(description);
        ingredientCommand.setAmount(amount);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(id);
        unitOfMeasureCommand.setName(description);
        ingredientCommand.setUom(unitOfMeasureCommand);
        ingredientCommand.setUom(unitOfMeasureCommand);
        ingredients.add(ingredientCommand);
        recipeCommand.setIngredients(ingredients);

        Recipe recipe = recipeConverter.convert(recipeCommand);
        assertNotNull(recipe);
        assertEquals(recipe.getId(), id);
        assertEquals(recipe.getPrepTime(), prepTime);
        assertEquals(recipe.getCookTime(), cookTime);
        assertEquals(recipe.getSource(), source);
        assertEquals(recipe.getServings(), servings);
        assertEquals(recipe.getDescription(), description);
        assertEquals(recipe.getDirections(), directions);
        assertEquals(recipe.getDifficulty(), difficulty);
        Note note = recipe.getNote();
        assertNotNull(note);
        assertEquals(note.getId(), id);
        assertEquals(note.getNotes(), description);
        Set<Ingredient> ingredients1 = recipe.getIngredients();
        assertNotNull(ingredients1);
        assertEquals(ingredients1.size(), 1);
        for (Ingredient ingredient: ingredients1) {
            assertNotNull(ingredient);
            assertEquals(ingredient.getId(), id);
        }
    }
}
