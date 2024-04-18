package ru.mts.entity.animals;

import ru.mts.entity.Predator;

import java.time.LocalDate;

public class Shark extends Predator {
    public Shark(String name, double cost, LocalDate birthDate) {
        super("Shark bread", name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "Shark" + super.toString();
    }
}


