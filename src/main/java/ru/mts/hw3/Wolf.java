package ru.mts.hw3;

public class Wolf extends Predator {
    public Wolf(String name, double cost) {
        super("Wolf bread", name, cost);
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}

