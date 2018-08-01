package guru.springframework.recipedemo.recipedemo.commands;

import guru.springframework.recipedemo.recipedemo.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String name;
    private Set<Recipe> recipes = new HashSet<>();
}
