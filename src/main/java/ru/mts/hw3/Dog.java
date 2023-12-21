package ru.mts.hw3;

public class Dog extends Pet {
    public Dog(String name, double cost) {
        super("Dog bread", name, cost);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}

