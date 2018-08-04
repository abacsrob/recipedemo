package guru.springframework.recipedemo.recipedemo.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CategoryCommand {
    private Long id;
    private String name;
}
