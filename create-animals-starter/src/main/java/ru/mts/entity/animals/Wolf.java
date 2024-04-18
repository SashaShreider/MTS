package ru.mts.entity.animals;

import ru.mts.entity.Predator;

import java.time.LocalDate;
import java.util.Objects;

public class Wolf extends Predator {
    public Wolf(String name, double cost, LocalDate birthDate) {
        super("Wolf bread", name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "Wolf" + super.toString();
    }
}

