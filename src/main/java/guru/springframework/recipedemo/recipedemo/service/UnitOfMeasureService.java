package guru.springframework.recipedemo.recipedemo.service;

import guru.springframework.recipedemo.recipedemo.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getAllUoms();
}
