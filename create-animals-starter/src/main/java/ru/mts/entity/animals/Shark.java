package ru.mts.entity.animals;

import ru.mts.entity.Predator;

import java.time.LocalDate;
import java.util.Objects;

public class Shark extends Predator {
    public Shark(String name, double cost, LocalDate birthDate) {
        super("Shark bread", name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "Shark" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }
}


