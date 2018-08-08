package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter) {
        this.unitOfMeasureConverter = unitOfMeasureConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        if (ingredient.getRecipe() != null) {
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUom(unitOfMeasureConverter.convert(ingredient.getUom()));
        return ingredientCommand;
    }
}
