package guru.springframework.recipedemo.recipedemo.repositories;

import guru.springframework.recipedemo.recipedemo.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByName(String name);
}
