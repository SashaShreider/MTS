package ru.mts.entity.animals;

import ru.mts.entity.Pet;

import java.time.LocalDate;
import java.util.Objects;

public class Dog extends Pet {
    public Dog(String name, double cost, LocalDate birthDate) {
        super("Dog bread", name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "Dog" + super.toString();
    }
}

