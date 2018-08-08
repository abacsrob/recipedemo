package guru.springframework.recipedemo.recipedemo.controllers;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.service.CategoryService;
import guru.springframework.recipedemo.recipedemo.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    RecipeController recipeController;

    MockMvc mockMvc;

    @Mock
    RecipeService recipeService;

    @Mock
    CategoryService categoryService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService, categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void testGetRecipe() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);

        Mockito.when(recipeService.findRecipeCommandById(Mockito.anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/show"));
    }

    @Test
    public void testGetNewRecipeForm() throws Exception {
        Mockito.when(categoryService.getCategoryCommands()).thenReturn(new HashSet<>());

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void testPostNewRecipeForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(12L);

        Mockito.when(recipeService.saveRecipeCommand(Mockito.any())).thenReturn(recipeCommand);

        mockMvc.perform(
                    post("/recipe")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("id", "")
                    .param("description", "Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/12/show"));
    }

    @Test
    public void testGetModifyView() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(12L);

        Mockito.when(recipeService.findRecipeCommandById(Mockito.anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/recipe/12/update"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        Mockito.verify(recipeService, Mockito.times(1)).deleteRecipe(Mockito.anyLong());
    }
}
