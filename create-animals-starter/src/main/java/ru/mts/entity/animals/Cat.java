package ru.mts.entity.animals;

import ru.mts.entity.Pet;

import java.time.LocalDate;

public class Cat extends Pet {
    public Cat(String name, double cost, LocalDate birthDate) {
        super("Cat bread", name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "Cat" + super.toString();
    }
}
