package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.CategoryCommand;
import guru.springframework.recipedemo.recipedemo.converters.CategoryToCategoryCommand;
import guru.springframework.recipedemo.recipedemo.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryToCategoryCommand converter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryToCategoryCommand converter) {
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    @Override
    public Set<CategoryCommand> getCategoryCommands() {
        Set<CategoryCommand> categories = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(category -> categories.add(converter.convert(category)));
        return categories;
    }
}
