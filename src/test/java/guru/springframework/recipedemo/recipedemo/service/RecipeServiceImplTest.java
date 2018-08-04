package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.converters.RecipeCommandToRecipe;
import guru.springframework.recipedemo.recipedemo.converters.RecipeToRecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import guru.springframework.recipedemo.recipedemo.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipes() {
        Recipe r = new Recipe();

        Set<Recipe> testRecipes = new HashSet<>();
        testRecipes.add(r);

        Mockito.when(recipeService.getRecipes()).thenReturn(testRecipes);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }


    @Test
    public void testGetRecipeById() {
        Long idValue = 5L;
        Recipe r = new Recipe();
        r.setId(idValue);
        Optional<Recipe> recipeOpt = Optional.of(r);

        Mockito.when(recipeRepository.findById(idValue)).thenReturn(recipeOpt);

        Recipe recipe = recipeService.findById(idValue);

        assertNotNull("recipe is null!", recipe);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository, Mockito.never()).findAll();

    }
}