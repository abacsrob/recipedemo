package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}