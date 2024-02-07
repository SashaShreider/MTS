package ru.mts.hw5.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Shark extends Predator {
    public Shark(String name, double cost, LocalDate birthDate) {
        super("Shark bread", name, cost, birthDate);
    }

    public Shark() {
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed) && Objects.equals(name, that.name) && Objects.equals(cost, that.cost) && Objects.equals(character, that.character) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, birthDate);
    }
}


