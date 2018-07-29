package guru.springframework.recipedemo.recipedemo.repositories;

import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
