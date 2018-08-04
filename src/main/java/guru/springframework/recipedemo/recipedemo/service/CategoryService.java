package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.CategoryCommand;

import java.util.Set;

public interface CategoryService {

    public Set<CategoryCommand> getCategoryCommands();
}
