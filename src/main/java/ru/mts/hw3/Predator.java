package ru.mts.hw3;

public abstract class Predator extends AbstractAnimal {

    public Predator(String breed, String name, double cost) {
        super(breed, name, cost, "aggressive");
    }
}
