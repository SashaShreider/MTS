package ru.mts.entity;

import java.time.LocalDate;

public abstract class Pet extends AbstractAnimal {
    protected Pet(String breed, String name, double cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate, "peacful");
    }
}

