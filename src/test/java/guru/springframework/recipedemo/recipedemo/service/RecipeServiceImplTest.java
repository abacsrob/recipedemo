package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import guru.springframework.recipedemo.recipedemo.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);
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
}