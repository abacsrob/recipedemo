package guru.springframework.recipedemo.recipedemo.repositories;

import guru.springframework.recipedemo.recipedemo.domain.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
