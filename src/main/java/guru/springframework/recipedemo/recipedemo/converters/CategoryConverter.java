package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.CategoryCommand;
import guru.springframework.recipedemo.recipedemo.service.CategoryService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

//@Component
//@ConfigurationPropertiesBinding
public class CategoryConverter implements Converter<String[], Set<CategoryCommand>> {

    CategoryService categoryService;

    public CategoryConverter(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Set<CategoryCommand> convert(String[] strings) {
        Set<CategoryCommand> categories = categoryService.getCategoryCommands();
        Set<CategoryCommand> result = new HashSet<>();
        for (String sId: strings) {
            Long categoryId = new Long(sId);
            for (CategoryCommand c: categories) {
                if (c.getId().equals(categoryId)) {
                    result.add(c);
                    break;
                }
            }
        }

        return categories;
    }
}
