package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.UnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.recipedemo.recipedemo.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureToUnitOfMeasureCommand converter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.converter = converter;
    }

    @Override
    public Set<UnitOfMeasureCommand> getAllUoms() {
//        Set<UnitOfMeasureCommand> uomCommands = new HashSet<>();
//        unitOfMeasureRepository.findAll().iterator().forEachRemaining(uom -> uomCommands.add(converter.convert(uom)));
//        return uomCommands;
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toSet());
    }
}
