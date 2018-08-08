package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.converters.IngredientCommandToIngredient;
import guru.springframework.recipedemo.recipedemo.converters.IngredientToIngredientCommand;
import guru.springframework.recipedemo.recipedemo.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.recipedemo.recipedemo.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import guru.springframework.recipedemo.recipedemo.repositories.IngredientRepository;
import guru.springframework.recipedemo.recipedemo.repositories.RecipeRepository;
import guru.springframework.recipedemo.recipedemo.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IngredientServiceImplTest {

    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientRepository, unitOfMeasureRepository, ingredientToIngredientCommand, ingredientCommandToIngredient);
    }

    @Test
    public void testFindByRecipeIdAndIngredientId() throws Exception {
    }

    @Test
    public void testFindByRecipeIdAndIngredientIdHappyPath() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1).addIngredient(ingredient2).addIngredient(ingredient3);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    public void testSaveRecipeIngredientCommand() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
        Mockito.when(recipeRepository.save(Mockito.any())).thenReturn(savedRecipe);

        IngredientCommand savedIngredientCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        assertEquals(Long.valueOf(3L), savedIngredientCommand.getId());
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository, Mockito.times(1)).save(Mockito.any(Recipe.class));
    }
}
