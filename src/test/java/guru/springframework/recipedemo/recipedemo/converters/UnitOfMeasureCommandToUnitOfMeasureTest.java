package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.UnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureConverter;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureConverter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNull() {
        assertNull(unitOfMeasureConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(unitOfMeasureConverter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String name = "name";

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(id);
        unitOfMeasureCommand.setName(name);

        UnitOfMeasure unitOfMeasure = unitOfMeasureConverter.convert(unitOfMeasureCommand);
        assertNotNull(unitOfMeasure);
        assertEquals(unitOfMeasure.getId(), id);
        assertEquals(unitOfMeasure.getName(), name);
    }
}
