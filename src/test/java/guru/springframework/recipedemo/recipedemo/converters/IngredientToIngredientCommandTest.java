package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    IngredientToIngredientCommand ingredientConverter;

    @Before
    public void setUp() throws Exception {
        ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNull() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String description = "description";
        BigDecimal amount = BigDecimal.ONE;

        Ingredient ingredient = new Ingredient();
        ingredient.setId(id);
        ingredient.setDescription(description);
        ingredient.setAmount(amount);

        IngredientCommand ingredientCommand = ingredientConverter.convert(ingredient);
        assertNotNull(ingredientCommand);
        assertEquals(ingredientCommand.getId(), id);

        assertEquals(ingredientCommand.getDescription(), description);
        assertEquals(ingredientCommand.getAmount(), amount);
    }
}
