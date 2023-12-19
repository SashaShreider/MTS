package ru.mts.hw2;

public class Cat extends Predator {
    public Cat(String name, double cost) {
        super("Cat bread", name, cost);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                '}';
    }
}
