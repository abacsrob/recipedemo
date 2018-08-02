package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.CategoryCommand;
import guru.springframework.recipedemo.recipedemo.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    CategoryCommandToCategory categoryConverter;

    @Before
    public void setUp() throws Exception {
        categoryConverter = new CategoryCommandToCategory();
    }

    @Test
    public void testNull() {
        assertNull(categoryConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(categoryConverter.convert(new CategoryCommand()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String name = "name";

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(id);
        categoryCommand.setName(name);

        Category category = categoryConverter.convert(categoryCommand);
        assertNotNull(category);
        assertEquals(category.getId(), id);
        assertEquals(category.getName(), name);
    }
}
