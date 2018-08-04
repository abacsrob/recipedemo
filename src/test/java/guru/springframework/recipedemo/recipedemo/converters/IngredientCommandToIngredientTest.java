package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.commands.UnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import guru.springframework.recipedemo.recipedemo.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    IngredientCommandToIngredient ingredientConverter;

    @Before
    public void setUp() throws Exception {
        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNull() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String description = "description";
        BigDecimal amount = BigDecimal.ONE;

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(id);
        ingredientCommand.setDescription(description);
        ingredientCommand.setAmount(amount);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(id);
        unitOfMeasureCommand.setName(description);
        ingredientCommand.setUom(unitOfMeasureCommand);

        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);
        assertNotNull(ingredient);
        assertEquals(ingredient.getId(), id);
        assertEquals(ingredient.getDescription(), description);
        assertEquals(ingredient.getAmount(), amount);
        UnitOfMeasure unitOfMeasure = ingredient.getUom();
        assertEquals(unitOfMeasure.getId(), id);
        assertEquals(unitOfMeasure.getName(), description);
    }
}
