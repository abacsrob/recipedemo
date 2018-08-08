package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.UnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.domain.UnitOfMeasure;
import guru.springframework.recipedemo.recipedemo.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;
    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    public UnitOfMeasureServiceImplTest() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, converter);
    }

    @Test
    public void testGetAllUoms() throws Exception {
        Set<UnitOfMeasure> uoms = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        uoms.add(uom1);
        uoms.add(uom2);
        Mockito.when(unitOfMeasureRepository.findAll()).thenReturn(uoms);

        Set<UnitOfMeasureCommand> uomCommands = unitOfMeasureService.getAllUoms();

        assertEquals(uomCommands.size(), 2);
        Mockito.verify(unitOfMeasureRepository, Mockito.times(1)).findAll();
    }
}
