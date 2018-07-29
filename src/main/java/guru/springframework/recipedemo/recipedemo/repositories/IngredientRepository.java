package guru.springframework.recipedemo.recipedemo.repositories;

import guru.springframework.recipedemo.recipedemo.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Optional<Ingredient> findByDescription(String description);
}
