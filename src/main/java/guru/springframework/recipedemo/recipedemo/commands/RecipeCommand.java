package guru.springframework.recipedemo.recipedemo.commands;

import guru.springframework.recipedemo.recipedemo.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private Integer source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;
    private NoteCommand note;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();

    public boolean hasCategory(CategoryCommand category) {
        boolean contains = categories.contains(category);
        return contains;
    }
}
