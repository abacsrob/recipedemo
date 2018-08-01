package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() throws Exception {
        ingredientToIngredientCommand = new IngredientToIngredientCommand();
    }

    @Test
    public void testNull() {
        assertNull(ingredientToIngredientCommand.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
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

        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
        assertNotNull(ingredientCommand);
        assertEquals(ingredientCommand.getId(), id);
        assertEquals(ingredientCommand.getDescription(), description);
        assertEquals(ingredientCommand.getAmount(), amount);
    }
}
