package guru.springframework.recipedemo.recipedemo.controllers;

import guru.springframework.recipedemo.recipedemo.commands.IngredientCommand;
import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.service.IngredientService;
import guru.springframework.recipedemo.recipedemo.service.RecipeService;
import guru.springframework.recipedemo.recipedemo.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    IngredientController ingredientController;

    MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        Mockito.when(recipeService.findRecipeCommandById(Mockito.anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/ingredient/list"));

        Mockito.verify(recipeService, Mockito.times(1)).findRecipeCommandById(Mockito.anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();

        Mockito.when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);

        mockMvc.perform(get("/recipe/1/ingredient/2/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }

    @Test
    public void testUpdateIngredientForm() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();

        Mockito.when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);
        Mockito.when(unitOfMeasureService.getAllUoms()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/form"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveUpdateRecipeIngredient() throws Exception {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(2L);

        Mockito.when(ingredientService.saveIngredientCommand(Mockito.any())).thenReturn(ingredientCommand);

        mockMvc.perform(post("/recipe/2/ingredient")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("id", "10")
                    .param("name", "name"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
    }
}
