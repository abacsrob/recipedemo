package guru.springframework.recipedemo.recipedemo.repositories;

import guru.springframework.recipedemo.recipedemo.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
