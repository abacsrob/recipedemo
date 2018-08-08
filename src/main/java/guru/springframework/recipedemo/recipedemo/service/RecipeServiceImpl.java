package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.RecipeCommand;
import guru.springframework.recipedemo.recipedemo.converters.RecipeCommandToRecipe;
import guru.springframework.recipedemo.recipedemo.converters.RecipeToRecipeCommand;
import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import guru.springframework.recipedemo.recipedemo.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {
        log.debug("Recipe Service: get Recipes");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public SortedSet<Recipe> getSortedRecipes(Sort sort) {
        SortedSet<Recipe> sortedRecipes = new TreeSet<>(Comparator.comparing(Recipe::getDescription));
        recipeRepository.findAll().iterator().forEachRemaining(sortedRecipes::add);
        return sortedRecipes;
    }

    public Recipe findById(Long id) {
        Optional<Recipe> r = recipeRepository.findById(id);
        if (!r.isPresent()) {
            throw new RuntimeException("Recipe not found!");
        }
        return r.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findRecipeCommandById(Long id) {
        Recipe recipe = findById(id);
        return recipeToRecipeCommand.convert(recipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
