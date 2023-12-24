package ru.mts.hw4.classe;

import java.time.LocalDate;

public abstract class Predator extends AbstractAnimal {

    protected Predator(String breed, String name, double cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate, "aggressive");
    }

    protected Predator() {
    }

}
