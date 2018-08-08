package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.converters.IngredientCommandToIngredient;
import guru.springframework.recipedemo.recipedemo.converters.IngredientToIngredientCommand;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import guru.springframework.recipedemo.recipedemo.repositories.IngredientRepository;
import guru.springframework.recipedemo.recipedemo.repositories.RecipeRepository;
import guru.springframework.recipedemo.recipedemo.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long id) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (!recipeOpt.isPresent()) {
            log.debug("Recipe with id " + recipeId + " not found!");
        }
        Recipe recipe = recipeOpt.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            log.debug("Ingredient with id " + id + " not found!");
        }
        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.error("Recipe with id = " + ingredientCommand.getRecipeId() + " not found!");
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream().filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId())).findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(ingredientCommand.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("Uom not found!")));
            } else {
                recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients()
                    .stream().filter(recipeIngredient -> recipeIngredient.getId().equals(ingredientCommand.getId()))
                    .findFirst().get());
        }
    }
}
