package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.CategoryCommand;
import guru.springframework.recipedemo.recipedemo.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    CategoryToCategoryCommand categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNull() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(categoryConverter.convert(new Category()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String name = "name";

        Category category = new Category();
        category.setId(id);
        category.setName(name);

        CategoryCommand categoryCommand = categoryConverter.convert(category);
        assertNotNull(categoryCommand);
        assertEquals(categoryCommand.getId(), id);
        assertEquals(categoryCommand.getName(), name);
    }
}
