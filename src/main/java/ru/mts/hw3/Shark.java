package ru.mts.hw3;

public class Shark extends Predator {
    public Shark(String name, double cost) {
        super("Shark bread", name, cost);
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}


