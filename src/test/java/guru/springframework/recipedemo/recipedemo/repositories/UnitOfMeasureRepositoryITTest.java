package guru.springframework.recipedemo.recipedemo.repositories;

import guru.springframework.recipedemo.recipedemo.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryITTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByNamePound() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByName("Pound");

        assertEquals(uom.get().getName(), "Pound");
    }

    @Test
    public void findByNameOunce() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByName("Ounce");

        assertEquals(uom.get().getName(), "Ounce");
    }
}