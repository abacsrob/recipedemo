package guru.springframework.recipedemo.recipedemo.converters;

import guru.springframework.recipedemo.recipedemo.commands.UnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureConverter;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNull() {
        assertNull(unitOfMeasureConverter.convert(null));
    }

    @Test
    public void testNotNull() {
        assertNotNull(unitOfMeasureConverter.convert(new UnitOfMeasure()));
    }

    @Test
    public void testConvert() {
        Long id = 1L;
        String name = "name";

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(id);
        unitOfMeasure.setName(name);

        UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureConverter.convert(unitOfMeasure);
        assertNotNull(unitOfMeasureCommand);
        assertEquals(unitOfMeasureCommand.getId(), id);
        assertEquals(unitOfMeasureCommand.getName(), name);
    }
}
